package com.sportup.sportfinder.web.rest;

import com.sportup.sportfinder.SportupApp;

import com.sportup.sportfinder.domain.Question;
import com.sportup.sportfinder.repository.QuestionRepository;
import com.sportup.sportfinder.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QuestionResource REST controller.
 *
 * @see QuestionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SportupApp.class)
public class QuestionResourceIntTest {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TO_MUSCLER = false;
    private static final Boolean UPDATED_TO_MUSCLER = true;

    private static final Boolean DEFAULT_TO_SECHER = false;
    private static final Boolean UPDATED_TO_SECHER = true;

    private static final Boolean DEFAULT_IS_COLLECTIF = false;
    private static final Boolean UPDATED_IS_COLLECTIF = true;

    private static final Boolean DEFAULT_TO_CIBLE_HAUT = false;
    private static final Boolean UPDATED_TO_CIBLE_HAUT = true;

    private static final Boolean DEFAULT_TO_CIBLE_BAS = false;
    private static final Boolean UPDATED_TO_CIBLE_BAS = true;

    private static final Boolean DEFAULT_IS_CONTACT = false;
    private static final Boolean UPDATED_IS_CONTACT = true;

    private static final Boolean DEFAULT_IS_BALLE = false;
    private static final Boolean UPDATED_IS_BALLE = true;

    private static final Boolean DEFAULT_IS_INTENSE = false;
    private static final Boolean UPDATED_IS_INTENSE = true;

    private static final Boolean DEFAULT_IS_COMBAT = false;
    private static final Boolean UPDATED_IS_COMBAT = true;

    private static final Boolean DEFAULT_IS_PERCUSSION = false;
    private static final Boolean UPDATED_IS_PERCUSSION = true;

    private static final Boolean DEFAULT_IS_CHER = false;
    private static final Boolean UPDATED_IS_CHER = true;

    private static final Boolean DEFAULT_IS_ARTISTIQUE = false;
    private static final Boolean UPDATED_IS_ARTISTIQUE = true;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restQuestionMockMvc;

    private Question question;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        QuestionResource questionResource = new QuestionResource(questionRepository);
        this.restQuestionMockMvc = MockMvcBuilders.standaloneSetup(questionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Question createEntity(EntityManager em) {
        Question question = new Question()
            .libelle(DEFAULT_LIBELLE)
            .toMuscler(DEFAULT_TO_MUSCLER)
            .toSecher(DEFAULT_TO_SECHER)
            .isCollectif(DEFAULT_IS_COLLECTIF)
            .toCibleHaut(DEFAULT_TO_CIBLE_HAUT)
            .toCibleBas(DEFAULT_TO_CIBLE_BAS)
            .isContact(DEFAULT_IS_CONTACT)
            .isBalle(DEFAULT_IS_BALLE)
            .isIntense(DEFAULT_IS_INTENSE)
            .isCombat(DEFAULT_IS_COMBAT)
            .isPercussion(DEFAULT_IS_PERCUSSION)
            .isCher(DEFAULT_IS_CHER)
            .isArtistique(DEFAULT_IS_ARTISTIQUE);
        return question;
    }

    @Before
    public void initTest() {
        question = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuestion() throws Exception {
        int databaseSizeBeforeCreate = questionRepository.findAll().size();

        // Create the Question
        restQuestionMockMvc.perform(post("/api/questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(question)))
            .andExpect(status().isCreated());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeCreate + 1);
        Question testQuestion = questionList.get(questionList.size() - 1);
        assertThat(testQuestion.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testQuestion.isToMuscler()).isEqualTo(DEFAULT_TO_MUSCLER);
        assertThat(testQuestion.isToSecher()).isEqualTo(DEFAULT_TO_SECHER);
        assertThat(testQuestion.isIsCollectif()).isEqualTo(DEFAULT_IS_COLLECTIF);
        assertThat(testQuestion.isToCibleHaut()).isEqualTo(DEFAULT_TO_CIBLE_HAUT);
        assertThat(testQuestion.isToCibleBas()).isEqualTo(DEFAULT_TO_CIBLE_BAS);
        assertThat(testQuestion.isIsContact()).isEqualTo(DEFAULT_IS_CONTACT);
        assertThat(testQuestion.isIsBalle()).isEqualTo(DEFAULT_IS_BALLE);
        assertThat(testQuestion.isIsIntense()).isEqualTo(DEFAULT_IS_INTENSE);
        assertThat(testQuestion.isIsCombat()).isEqualTo(DEFAULT_IS_COMBAT);
        assertThat(testQuestion.isIsPercussion()).isEqualTo(DEFAULT_IS_PERCUSSION);
        assertThat(testQuestion.isIsCher()).isEqualTo(DEFAULT_IS_CHER);
        assertThat(testQuestion.isIsArtistique()).isEqualTo(DEFAULT_IS_ARTISTIQUE);
    }

    @Test
    @Transactional
    public void createQuestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = questionRepository.findAll().size();

        // Create the Question with an existing ID
        question.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionMockMvc.perform(post("/api/questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(question)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllQuestions() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        // Get all the questionList
        restQuestionMockMvc.perform(get("/api/questions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(question.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].toMuscler").value(hasItem(DEFAULT_TO_MUSCLER.booleanValue())))
            .andExpect(jsonPath("$.[*].toSecher").value(hasItem(DEFAULT_TO_SECHER.booleanValue())))
            .andExpect(jsonPath("$.[*].isCollectif").value(hasItem(DEFAULT_IS_COLLECTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].toCibleHaut").value(hasItem(DEFAULT_TO_CIBLE_HAUT.booleanValue())))
            .andExpect(jsonPath("$.[*].toCibleBas").value(hasItem(DEFAULT_TO_CIBLE_BAS.booleanValue())))
            .andExpect(jsonPath("$.[*].isContact").value(hasItem(DEFAULT_IS_CONTACT.booleanValue())))
            .andExpect(jsonPath("$.[*].isBalle").value(hasItem(DEFAULT_IS_BALLE.booleanValue())))
            .andExpect(jsonPath("$.[*].isIntense").value(hasItem(DEFAULT_IS_INTENSE.booleanValue())))
            .andExpect(jsonPath("$.[*].isCombat").value(hasItem(DEFAULT_IS_COMBAT.booleanValue())))
            .andExpect(jsonPath("$.[*].isPercussion").value(hasItem(DEFAULT_IS_PERCUSSION.booleanValue())))
            .andExpect(jsonPath("$.[*].isCher").value(hasItem(DEFAULT_IS_CHER.booleanValue())))
            .andExpect(jsonPath("$.[*].isArtistique").value(hasItem(DEFAULT_IS_ARTISTIQUE.booleanValue())));
    }

    @Test
    @Transactional
    public void getQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        // Get the question
        restQuestionMockMvc.perform(get("/api/questions/{id}", question.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(question.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.toMuscler").value(DEFAULT_TO_MUSCLER.booleanValue()))
            .andExpect(jsonPath("$.toSecher").value(DEFAULT_TO_SECHER.booleanValue()))
            .andExpect(jsonPath("$.isCollectif").value(DEFAULT_IS_COLLECTIF.booleanValue()))
            .andExpect(jsonPath("$.toCibleHaut").value(DEFAULT_TO_CIBLE_HAUT.booleanValue()))
            .andExpect(jsonPath("$.toCibleBas").value(DEFAULT_TO_CIBLE_BAS.booleanValue()))
            .andExpect(jsonPath("$.isContact").value(DEFAULT_IS_CONTACT.booleanValue()))
            .andExpect(jsonPath("$.isBalle").value(DEFAULT_IS_BALLE.booleanValue()))
            .andExpect(jsonPath("$.isIntense").value(DEFAULT_IS_INTENSE.booleanValue()))
            .andExpect(jsonPath("$.isCombat").value(DEFAULT_IS_COMBAT.booleanValue()))
            .andExpect(jsonPath("$.isPercussion").value(DEFAULT_IS_PERCUSSION.booleanValue()))
            .andExpect(jsonPath("$.isCher").value(DEFAULT_IS_CHER.booleanValue()))
            .andExpect(jsonPath("$.isArtistique").value(DEFAULT_IS_ARTISTIQUE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQuestion() throws Exception {
        // Get the question
        restQuestionMockMvc.perform(get("/api/questions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Update the question
        Question updatedQuestion = questionRepository.findOne(question.getId());
        updatedQuestion
            .libelle(UPDATED_LIBELLE)
            .toMuscler(UPDATED_TO_MUSCLER)
            .toSecher(UPDATED_TO_SECHER)
            .isCollectif(UPDATED_IS_COLLECTIF)
            .toCibleHaut(UPDATED_TO_CIBLE_HAUT)
            .toCibleBas(UPDATED_TO_CIBLE_BAS)
            .isContact(UPDATED_IS_CONTACT)
            .isBalle(UPDATED_IS_BALLE)
            .isIntense(UPDATED_IS_INTENSE)
            .isCombat(UPDATED_IS_COMBAT)
            .isPercussion(UPDATED_IS_PERCUSSION)
            .isCher(UPDATED_IS_CHER)
            .isArtistique(UPDATED_IS_ARTISTIQUE);

        restQuestionMockMvc.perform(put("/api/questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedQuestion)))
            .andExpect(status().isOk());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
        Question testQuestion = questionList.get(questionList.size() - 1);
        assertThat(testQuestion.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testQuestion.isToMuscler()).isEqualTo(UPDATED_TO_MUSCLER);
        assertThat(testQuestion.isToSecher()).isEqualTo(UPDATED_TO_SECHER);
        assertThat(testQuestion.isIsCollectif()).isEqualTo(UPDATED_IS_COLLECTIF);
        assertThat(testQuestion.isToCibleHaut()).isEqualTo(UPDATED_TO_CIBLE_HAUT);
        assertThat(testQuestion.isToCibleBas()).isEqualTo(UPDATED_TO_CIBLE_BAS);
        assertThat(testQuestion.isIsContact()).isEqualTo(UPDATED_IS_CONTACT);
        assertThat(testQuestion.isIsBalle()).isEqualTo(UPDATED_IS_BALLE);
        assertThat(testQuestion.isIsIntense()).isEqualTo(UPDATED_IS_INTENSE);
        assertThat(testQuestion.isIsCombat()).isEqualTo(UPDATED_IS_COMBAT);
        assertThat(testQuestion.isIsPercussion()).isEqualTo(UPDATED_IS_PERCUSSION);
        assertThat(testQuestion.isIsCher()).isEqualTo(UPDATED_IS_CHER);
        assertThat(testQuestion.isIsArtistique()).isEqualTo(UPDATED_IS_ARTISTIQUE);
    }

    @Test
    @Transactional
    public void updateNonExistingQuestion() throws Exception {
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Create the Question

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restQuestionMockMvc.perform(put("/api/questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(question)))
            .andExpect(status().isCreated());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);
        int databaseSizeBeforeDelete = questionRepository.findAll().size();

        // Get the question
        restQuestionMockMvc.perform(delete("/api/questions/{id}", question.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Question.class);
        Question question1 = new Question();
        question1.setId(1L);
        Question question2 = new Question();
        question2.setId(question1.getId());
        assertThat(question1).isEqualTo(question2);
        question2.setId(2L);
        assertThat(question1).isNotEqualTo(question2);
        question1.setId(null);
        assertThat(question1).isNotEqualTo(question2);
    }
}
