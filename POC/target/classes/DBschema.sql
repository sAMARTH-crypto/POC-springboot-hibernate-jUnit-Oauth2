#sample queries
#database will be created parallel while the execution because of hibernate
#so run these queries only if necessary 
#table

CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fName VARCHAR(255),
    lName VARCHAR(255),
    department VARCHAR(255),
    designation VARCHAR(255),
    age INT
);

#test data - mandatory for GETmethods

INSERT INTO employee (f_name, l_name, department, designation, age)
VALUES
    ('John', 'Doe', 'HR', 'Manager', 35),
    ('Jane', 'Smith', 'Sales', 'Sales Representative', 28),
    ('Michael', 'Johnson', 'IT', 'Software Engineer', 32);