package com.sportup.sportfinder.web.rest;

import com.sportup.sportfinder.SportupApp;

import com.sportup.sportfinder.domain.Sport;
import com.sportup.sportfinder.repository.SportRepository;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SportResource REST controller.
 *
 * @see SportResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SportupApp.class)
public class SportResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

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
    private SportRepository sportRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSportMockMvc;

    private Sport sport;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SportResource sportResource = new SportResource(sportRepository);
        this.restSportMockMvc = MockMvcBuilders.standaloneSetup(sportResource)
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
    public static Sport createEntity(EntityManager em) {
        Sport sport = new Sport()
            .nom(DEFAULT_NOM)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
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
        return sport;
    }

    @Before
    public void initTest() {
        sport = createEntity(em);
    }

    @Test
    @Transactional
    public void createSport() throws Exception {
        int databaseSizeBeforeCreate = sportRepository.findAll().size();

        // Create the Sport
        restSportMockMvc.perform(post("/api/sports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sport)))
            .andExpect(status().isCreated());

        // Validate the Sport in the database
        List<Sport> sportList = sportRepository.findAll();
        assertThat(sportList).hasSize(databaseSizeBeforeCreate + 1);
        Sport testSport = sportList.get(sportList.size() - 1);
        assertThat(testSport.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testSport.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testSport.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testSport.isToMuscler()).isEqualTo(DEFAULT_TO_MUSCLER);
        assertThat(testSport.isToSecher()).isEqualTo(DEFAULT_TO_SECHER);
        assertThat(testSport.isIsCollectif()).isEqualTo(DEFAULT_IS_COLLECTIF);
        assertThat(testSport.isToCibleHaut()).isEqualTo(DEFAULT_TO_CIBLE_HAUT);
        assertThat(testSport.isToCibleBas()).isEqualTo(DEFAULT_TO_CIBLE_BAS);
        assertThat(testSport.isIsContact()).isEqualTo(DEFAULT_IS_CONTACT);
        assertThat(testSport.isIsBalle()).isEqualTo(DEFAULT_IS_BALLE);
        assertThat(testSport.isIsIntense()).isEqualTo(DEFAULT_IS_INTENSE);
        assertThat(testSport.isIsCombat()).isEqualTo(DEFAULT_IS_COMBAT);
        assertThat(testSport.isIsPercussion()).isEqualTo(DEFAULT_IS_PERCUSSION);
        assertThat(testSport.isIsCher()).isEqualTo(DEFAULT_IS_CHER);
        assertThat(testSport.isIsArtistique()).isEqualTo(DEFAULT_IS_ARTISTIQUE);
    }

    @Test
    @Transactional
    public void createSportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sportRepository.findAll().size();

        // Create the Sport with an existing ID
        sport.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSportMockMvc.perform(post("/api/sports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sport)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Sport> sportList = sportRepository.findAll();
        assertThat(sportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSports() throws Exception {
        // Initialize the database
        sportRepository.saveAndFlush(sport);

        // Get all the sportList
        restSportMockMvc.perform(get("/api/sports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sport.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
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
    public void getSport() throws Exception {
        // Initialize the database
        sportRepository.saveAndFlush(sport);

        // Get the sport
        restSportMockMvc.perform(get("/api/sports/{id}", sport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sport.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
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
    public void getNonExistingSport() throws Exception {
        // Get the sport
        restSportMockMvc.perform(get("/api/sports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSport() throws Exception {
        // Initialize the database
        sportRepository.saveAndFlush(sport);
        int databaseSizeBeforeUpdate = sportRepository.findAll().size();

        // Update the sport
        Sport updatedSport = sportRepository.findOne(sport.getId());
        updatedSport
            .nom(UPDATED_NOM)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
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

        restSportMockMvc.perform(put("/api/sports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSport)))
            .andExpect(status().isOk());

        // Validate the Sport in the database
        List<Sport> sportList = sportRepository.findAll();
        assertThat(sportList).hasSize(databaseSizeBeforeUpdate);
        Sport testSport = sportList.get(sportList.size() - 1);
        assertThat(testSport.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testSport.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testSport.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testSport.isToMuscler()).isEqualTo(UPDATED_TO_MUSCLER);
        assertThat(testSport.isToSecher()).isEqualTo(UPDATED_TO_SECHER);
        assertThat(testSport.isIsCollectif()).isEqualTo(UPDATED_IS_COLLECTIF);
        assertThat(testSport.isToCibleHaut()).isEqualTo(UPDATED_TO_CIBLE_HAUT);
        assertThat(testSport.isToCibleBas()).isEqualTo(UPDATED_TO_CIBLE_BAS);
        assertThat(testSport.isIsContact()).isEqualTo(UPDATED_IS_CONTACT);
        assertThat(testSport.isIsBalle()).isEqualTo(UPDATED_IS_BALLE);
        assertThat(testSport.isIsIntense()).isEqualTo(UPDATED_IS_INTENSE);
        assertThat(testSport.isIsCombat()).isEqualTo(UPDATED_IS_COMBAT);
        assertThat(testSport.isIsPercussion()).isEqualTo(UPDATED_IS_PERCUSSION);
        assertThat(testSport.isIsCher()).isEqualTo(UPDATED_IS_CHER);
        assertThat(testSport.isIsArtistique()).isEqualTo(UPDATED_IS_ARTISTIQUE);
    }

    @Test
    @Transactional
    public void updateNonExistingSport() throws Exception {
        int databaseSizeBeforeUpdate = sportRepository.findAll().size();

        // Create the Sport

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSportMockMvc.perform(put("/api/sports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sport)))
            .andExpect(status().isCreated());

        // Validate the Sport in the database
        List<Sport> sportList = sportRepository.findAll();
        assertThat(sportList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSport() throws Exception {
        // Initialize the database
        sportRepository.saveAndFlush(sport);
        int databaseSizeBeforeDelete = sportRepository.findAll().size();

        // Get the sport
        restSportMockMvc.perform(delete("/api/sports/{id}", sport.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sport> sportList = sportRepository.findAll();
        assertThat(sportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sport.class);
        Sport sport1 = new Sport();
        sport1.setId(1L);
        Sport sport2 = new Sport();
        sport2.setId(sport1.getId());
        assertThat(sport1).isEqualTo(sport2);
        sport2.setId(2L);
        assertThat(sport1).isNotEqualTo(sport2);
        sport1.setId(null);
        assertThat(sport1).isNotEqualTo(sport2);
    }
}
