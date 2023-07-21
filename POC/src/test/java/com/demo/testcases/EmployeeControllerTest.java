package com.demo.testcases;
import com.demo.model.*;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;



import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;




import com.fasterxml.jackson.annotation.JsonInclude;


@SpringBootTest
//@SpringBootTest(classes = com.demo.Poc19thFinalApplication.class)

@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetAllEmps() throws Exception {
        // Mock the service response
        List<Employee> employees = Arrays.asList(
            new Employee(1, "John", "Doe", "IT", "Engineer", "30"),
            new Employee(2, "Jane", "Smith", "HR", "Manager", "35")
        );
        Mockito.when(employeeService.getEmployees()).thenReturn(employees);

        // Perform the GET request
        mockMvc.perform(get("/getEmps"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2))) // Check if the response contains 2 employees
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].firstName").value("John"))
        .andExpect(jsonPath("$[0].lastName").value("Doe"))
        .andExpect(jsonPath("$[0].depart").value("IT"))
        .andExpect(jsonPath("$[0].desig").value("Engineer"))
        .andExpect(jsonPath("$[0].age").value("30"))
        .andExpect(jsonPath("$[1].id").value(2))
        .andExpect(jsonPath("$[1].firstName").value("Jane"))
        .andExpect(jsonPath("$[1].lastName").value("Smith"))
        .andExpect(jsonPath("$[1].depart").value("HR"))
        .andExpect(jsonPath("$[1].desig").value("Manager"))
        .andExpect(jsonPath("$[1].age").value("35"));
    }
    
    
    @Test
    public void testGetEmployeeById() throws Exception {
        int employeeId = 1;

        // Mock the service response for getting an employee by ID
        Employee employee = new Employee(1, "John", "Doe", "IT", "Engineer", "30");
        Mockito.when(employeeService.getEmployeeByID(employeeId)).thenReturn(employee);

        // Perform the GET request to get an employee by ID
        mockMvc.perform(get("/getempbyid/{id}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employeeId))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.depart").value("IT"))
                .andExpect(jsonPath("$.desig").value("Engineer"))
                .andExpect(jsonPath("$.age").value("30"));
    }
    
    @Test
    public void testAddEmp() throws Exception {
        // Mock the service response for saving an employee
        Employee employeeToAdd = new Employee(1, "John", "Doe", "IT", "Engineer", "30");
        Mockito.when(employeeService.saveEmployee(Mockito.any(Employee.class)))
                .thenReturn(employeeToAdd);

        // Perform the POST request to add an employee
        mockMvc.perform(post("/addemp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employeeToAdd)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")));
    }
    
    @Test
    public void testDeleteEmp() throws Exception {
        int employeeIdToDelete = 1;
        
        // Mock the service response for deleting an employee
        Mockito.when(employeeService.deleteRecord(employeeIdToDelete)).thenReturn("Employee deleted successfully");

        // Perform the DELETE request to delete an employee
        mockMvc.perform(delete("/delete/{id}", employeeIdToDelete))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Employee deleted successfully"));
    }
    
    @Test
    public void testUpdateEmp() throws Exception {
        // Mock the service response for updating an employee
        Employee updatedEmployee = new Employee(1, "John", "Doe", "IT", "Manager", "32");
        Mockito.when(employeeService.updateEmployee(Mockito.any(Employee.class)))
                .thenReturn(updatedEmployee);

        // Perform the PUT request to update an employee
        mockMvc.perform(put("/updateemp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.desig", is("Manager")))
                .andExpect(jsonPath("$.age", is("32")));
    }
    
    
 // Helper method to convert object to JSON string
    private static String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

    // Add more test methods for other endpoints, error scenarios, etc.

