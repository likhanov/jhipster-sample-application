package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Order;
import com.mycompany.myapp.repository.OrderRepository;
import com.mycompany.myapp.service.OrderService;

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
 * Integration tests for the {@link OrderResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class OrderResourceIT {

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final String DEFAULT_COLLECTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COLLECTION_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CANCELLED = false;
    private static final Boolean UPDATED_IS_CANCELLED = true;

    private static final String DEFAULT_NOMINEE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NOMINEE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FACTORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FACTORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_C_IL_QUANTITY = 1L;
    private static final Long UPDATED_C_IL_QUANTITY = 2L;

    private static final Long DEFAULT_C_IL_REQUEST_QUANTITY = 1L;
    private static final Long UPDATED_C_IL_REQUEST_QUANTITY = 2L;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MockMvc restOrderMockMvc;

    private Order order;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Order createEntity() {
        Order order = new Order()
            .orderID(DEFAULT_ORDER_ID)
            .version(DEFAULT_VERSION)
            .collectionName(DEFAULT_COLLECTION_NAME)
            .isCancelled(DEFAULT_IS_CANCELLED)
            .nomineeName(DEFAULT_NOMINEE_NAME)
            .vendorName(DEFAULT_VENDOR_NAME)
            .factoryName(DEFAULT_FACTORY_NAME)
            .deliveryAddress(DEFAULT_DELIVERY_ADDRESS)
            .comment(DEFAULT_COMMENT)
            .cILQuantity(DEFAULT_C_IL_QUANTITY)
            .cILRequestQuantity(DEFAULT_C_IL_REQUEST_QUANTITY);
        return order;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Order createUpdatedEntity() {
        Order order = new Order()
            .orderID(UPDATED_ORDER_ID)
            .version(UPDATED_VERSION)
            .collectionName(UPDATED_COLLECTION_NAME)
            .isCancelled(UPDATED_IS_CANCELLED)
            .nomineeName(UPDATED_NOMINEE_NAME)
            .vendorName(UPDATED_VENDOR_NAME)
            .factoryName(UPDATED_FACTORY_NAME)
            .deliveryAddress(UPDATED_DELIVERY_ADDRESS)
            .comment(UPDATED_COMMENT)
            .cILQuantity(UPDATED_C_IL_QUANTITY)
            .cILRequestQuantity(UPDATED_C_IL_REQUEST_QUANTITY);
        return order;
    }

    @BeforeEach
    public void initTest() {
        orderRepository.deleteAll();
        order = createEntity();
    }

    @Test
    public void createOrder() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();

        // Create the Order
        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isCreated());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate + 1);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getOrderID()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testOrder.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testOrder.getCollectionName()).isEqualTo(DEFAULT_COLLECTION_NAME);
        assertThat(testOrder.isIsCancelled()).isEqualTo(DEFAULT_IS_CANCELLED);
        assertThat(testOrder.getNomineeName()).isEqualTo(DEFAULT_NOMINEE_NAME);
        assertThat(testOrder.getVendorName()).isEqualTo(DEFAULT_VENDOR_NAME);
        assertThat(testOrder.getFactoryName()).isEqualTo(DEFAULT_FACTORY_NAME);
        assertThat(testOrder.getDeliveryAddress()).isEqualTo(DEFAULT_DELIVERY_ADDRESS);
        assertThat(testOrder.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testOrder.getcILQuantity()).isEqualTo(DEFAULT_C_IL_QUANTITY);
        assertThat(testOrder.getcILRequestQuantity()).isEqualTo(DEFAULT_C_IL_REQUEST_QUANTITY);
    }

    @Test
    public void createOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();

        // Create the Order with an existing ID
        order.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkOrderIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setOrderID(null);

        // Create the Order, which fails.

        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOrders() throws Exception {
        // Initialize the database
        orderRepository.save(order);

        // Get all the orderList
        restOrderMockMvc.perform(get("/api/orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(order.getId())))
            .andExpect(jsonPath("$.[*].orderID").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].collectionName").value(hasItem(DEFAULT_COLLECTION_NAME)))
            .andExpect(jsonPath("$.[*].isCancelled").value(hasItem(DEFAULT_IS_CANCELLED.booleanValue())))
            .andExpect(jsonPath("$.[*].nomineeName").value(hasItem(DEFAULT_NOMINEE_NAME)))
            .andExpect(jsonPath("$.[*].vendorName").value(hasItem(DEFAULT_VENDOR_NAME)))
            .andExpect(jsonPath("$.[*].factoryName").value(hasItem(DEFAULT_FACTORY_NAME)))
            .andExpect(jsonPath("$.[*].deliveryAddress").value(hasItem(DEFAULT_DELIVERY_ADDRESS)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].cILQuantity").value(hasItem(DEFAULT_C_IL_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].cILRequestQuantity").value(hasItem(DEFAULT_C_IL_REQUEST_QUANTITY.intValue())));
    }
    
    @Test
    public void getOrder() throws Exception {
        // Initialize the database
        orderRepository.save(order);

        // Get the order
        restOrderMockMvc.perform(get("/api/orders/{id}", order.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(order.getId()))
            .andExpect(jsonPath("$.orderID").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.collectionName").value(DEFAULT_COLLECTION_NAME))
            .andExpect(jsonPath("$.isCancelled").value(DEFAULT_IS_CANCELLED.booleanValue()))
            .andExpect(jsonPath("$.nomineeName").value(DEFAULT_NOMINEE_NAME))
            .andExpect(jsonPath("$.vendorName").value(DEFAULT_VENDOR_NAME))
            .andExpect(jsonPath("$.factoryName").value(DEFAULT_FACTORY_NAME))
            .andExpect(jsonPath("$.deliveryAddress").value(DEFAULT_DELIVERY_ADDRESS))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.cILQuantity").value(DEFAULT_C_IL_QUANTITY.intValue()))
            .andExpect(jsonPath("$.cILRequestQuantity").value(DEFAULT_C_IL_REQUEST_QUANTITY.intValue()));
    }

    @Test
    public void getNonExistingOrder() throws Exception {
        // Get the order
        restOrderMockMvc.perform(get("/api/orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOrder() throws Exception {
        // Initialize the database
        orderService.save(order);

        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Update the order
        Order updatedOrder = orderRepository.findById(order.getId()).get();
        updatedOrder
            .orderID(UPDATED_ORDER_ID)
            .version(UPDATED_VERSION)
            .collectionName(UPDATED_COLLECTION_NAME)
            .isCancelled(UPDATED_IS_CANCELLED)
            .nomineeName(UPDATED_NOMINEE_NAME)
            .vendorName(UPDATED_VENDOR_NAME)
            .factoryName(UPDATED_FACTORY_NAME)
            .deliveryAddress(UPDATED_DELIVERY_ADDRESS)
            .comment(UPDATED_COMMENT)
            .cILQuantity(UPDATED_C_IL_QUANTITY)
            .cILRequestQuantity(UPDATED_C_IL_REQUEST_QUANTITY);

        restOrderMockMvc.perform(put("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrder)))
            .andExpect(status().isOk());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getOrderID()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOrder.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testOrder.getCollectionName()).isEqualTo(UPDATED_COLLECTION_NAME);
        assertThat(testOrder.isIsCancelled()).isEqualTo(UPDATED_IS_CANCELLED);
        assertThat(testOrder.getNomineeName()).isEqualTo(UPDATED_NOMINEE_NAME);
        assertThat(testOrder.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
        assertThat(testOrder.getFactoryName()).isEqualTo(UPDATED_FACTORY_NAME);
        assertThat(testOrder.getDeliveryAddress()).isEqualTo(UPDATED_DELIVERY_ADDRESS);
        assertThat(testOrder.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testOrder.getcILQuantity()).isEqualTo(UPDATED_C_IL_QUANTITY);
        assertThat(testOrder.getcILRequestQuantity()).isEqualTo(UPDATED_C_IL_REQUEST_QUANTITY);
    }

    @Test
    public void updateNonExistingOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Create the Order

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMockMvc.perform(put("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(order)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOrder() throws Exception {
        // Initialize the database
        orderService.save(order);

        int databaseSizeBeforeDelete = orderRepository.findAll().size();

        // Delete the order
        restOrderMockMvc.perform(delete("/api/orders/{id}", order.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
