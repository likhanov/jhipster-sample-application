package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Item;
import com.mycompany.myapp.repository.ItemRepository;
import com.mycompany.myapp.service.ItemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ItemResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ItemResourceIT {

    private static final String DEFAULT_P_POID = "AAAAAAAAAA";
    private static final String UPDATED_P_POID = "BBBBBBBBBB";

    private static final String DEFAULT_LABEL_ID = "AAAAAAAAAA";
    private static final String UPDATED_LABEL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_T_D_CODE = "AAAAAAAAAA";
    private static final String UPDATED_T_D_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final String DEFAULT_SALES_COUNTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SALES_COUNTRY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_STICKER_ID = "AAAAAAAAAA";
    private static final String UPDATED_STICKER_ID = "BBBBBBBBBB";

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MockMvc restItemMockMvc;

    private Item item;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Item createEntity() {
        Item item = new Item()
            .pPOID(DEFAULT_P_POID)
            .labelID(DEFAULT_LABEL_ID)
            .tDCode(DEFAULT_T_D_CODE)
            .quantity(DEFAULT_QUANTITY)
            .salesCountryName(DEFAULT_SALES_COUNTRY_NAME)
            .comment(DEFAULT_COMMENT)
            .stickerID(DEFAULT_STICKER_ID);
        return item;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Item createUpdatedEntity() {
        Item item = new Item()
            .pPOID(UPDATED_P_POID)
            .labelID(UPDATED_LABEL_ID)
            .tDCode(UPDATED_T_D_CODE)
            .quantity(UPDATED_QUANTITY)
            .salesCountryName(UPDATED_SALES_COUNTRY_NAME)
            .comment(UPDATED_COMMENT)
            .stickerID(UPDATED_STICKER_ID);
        return item;
    }

    @BeforeEach
    public void initTest() {
        itemRepository.deleteAll();
        item = createEntity();
    }

    @Test
    public void createItem() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();

        // Create the Item
        restItemMockMvc.perform(post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isCreated());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate + 1);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getpPOID()).isEqualTo(DEFAULT_P_POID);
        assertThat(testItem.getLabelID()).isEqualTo(DEFAULT_LABEL_ID);
        assertThat(testItem.gettDCode()).isEqualTo(DEFAULT_T_D_CODE);
        assertThat(testItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testItem.getSalesCountryName()).isEqualTo(DEFAULT_SALES_COUNTRY_NAME);
        assertThat(testItem.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testItem.getStickerID()).isEqualTo(DEFAULT_STICKER_ID);
    }

    @Test
    public void createItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();

        // Create the Item with an existing ID
        item.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemMockMvc.perform(post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkpPOIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setpPOID(null);

        // Create the Item, which fails.

        restItemMockMvc.perform(post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllItems() throws Exception {
        // Initialize the database
        itemRepository.save(item);

        // Get all the itemList
        restItemMockMvc.perform(get("/api/items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(item.getId())))
            .andExpect(jsonPath("$.[*].pPOID").value(hasItem(DEFAULT_P_POID)))
            .andExpect(jsonPath("$.[*].labelID").value(hasItem(DEFAULT_LABEL_ID)))
            .andExpect(jsonPath("$.[*].tDCode").value(hasItem(DEFAULT_T_D_CODE)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].salesCountryName").value(hasItem(DEFAULT_SALES_COUNTRY_NAME)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].stickerID").value(hasItem(DEFAULT_STICKER_ID)));
    }
    
    @Test
    public void getItem() throws Exception {
        // Initialize the database
        itemRepository.save(item);

        // Get the item
        restItemMockMvc.perform(get("/api/items/{id}", item.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(item.getId()))
            .andExpect(jsonPath("$.pPOID").value(DEFAULT_P_POID))
            .andExpect(jsonPath("$.labelID").value(DEFAULT_LABEL_ID))
            .andExpect(jsonPath("$.tDCode").value(DEFAULT_T_D_CODE))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.salesCountryName").value(DEFAULT_SALES_COUNTRY_NAME))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.stickerID").value(DEFAULT_STICKER_ID));
    }

    @Test
    public void getNonExistingItem() throws Exception {
        // Get the item
        restItemMockMvc.perform(get("/api/items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateItem() throws Exception {
        // Initialize the database
        itemService.save(item);

        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Update the item
        Item updatedItem = itemRepository.findById(item.getId()).get();
        updatedItem
            .pPOID(UPDATED_P_POID)
            .labelID(UPDATED_LABEL_ID)
            .tDCode(UPDATED_T_D_CODE)
            .quantity(UPDATED_QUANTITY)
            .salesCountryName(UPDATED_SALES_COUNTRY_NAME)
            .comment(UPDATED_COMMENT)
            .stickerID(UPDATED_STICKER_ID);

        restItemMockMvc.perform(put("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedItem)))
            .andExpect(status().isOk());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getpPOID()).isEqualTo(UPDATED_P_POID);
        assertThat(testItem.getLabelID()).isEqualTo(UPDATED_LABEL_ID);
        assertThat(testItem.gettDCode()).isEqualTo(UPDATED_T_D_CODE);
        assertThat(testItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testItem.getSalesCountryName()).isEqualTo(UPDATED_SALES_COUNTRY_NAME);
        assertThat(testItem.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testItem.getStickerID()).isEqualTo(UPDATED_STICKER_ID);
    }

    @Test
    public void updateNonExistingItem() throws Exception {
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Create the Item

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemMockMvc.perform(put("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteItem() throws Exception {
        // Initialize the database
        itemService.save(item);

        int databaseSizeBeforeDelete = itemRepository.findAll().size();

        // Delete the item
        restItemMockMvc.perform(delete("/api/items/{id}", item.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
