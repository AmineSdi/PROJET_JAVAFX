DROP Table IF EXISTS MedicalHistories;
DROP Table IF EXISTS MedicalVisits;
DROP Table IF EXISTS Doctors;
DROP Table IF EXISTS MedicalEstablishments;
DROP Table IF EXISTS HealthProfessionals;
DROP Table IF EXISTS PatientFiles;
DROP Table IF EXISTS Users;
DROP Table IF EXISTS ContactInformation;


CREATE Table Users (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   firstName varchar(30) NOT NULL,
   lastName varchar(30) NOT NULL,
   username text NOT NULL UNIQUE,
   password text NOT NULL
);

CREATE Table HealthProfessionals (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   userId INTEGER NOT NULL,
   FOREIGN KEY (userID) REFERENCES Users(id)
);

CREATE Table ContactInformation (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    number INTEGER NOT NULL,
    street varchar(30),
    city varchar (30),
    postalCode varchar (6), 
    phone varchar(13),
    email varchar(30)
);

CREATE Table MedicalEstablishments(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name varchar(150) NOT NULL, 
    contactInfoId INTEGER NOT NULL, 
    FOREIGN KEY (contactInfoId) REFERENCES ContactInformation(id)    
);

CREATE Table Doctors (
   license INTEGER(5) PRIMARY KEY,
   specialty varchar(50) NOT NULL,
   healthProfessionalId INTEGER NOT NULL, 
   medicalEstablishmentId INTEGER NOT NULL,
   FOREIGN KEY (healthProfessionalId) REFERENCES HealthProfessionals(id), 
   FOREIGN KEY (medicalEstablishmentId) REFERENCES MedicalEstablishments(id)
);

CREATE Table PatientFiles (
   ramqCode varchar(12) PRIMARY KEY,
   firstName varchar(30) NOT NULL,
   lastName varchar(30) NOT NULL,
   gender text CHECK( gender IN ('FEMALE','MALE','OTHER') ) NOT NULL,
   birthCity varchar(30) NOT NULL,
   birthDate text NOT NULL,
   parentsName varchar(80) NOT NULL,
   contactInfoId INTEGER NOT NULL, 
   FOREIGN KEY (contactInfoId) REFERENCES ContactInformation(id)
);

CREATE Table MedicalHistories (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   patientRamqCode varchar(12) NOT NULL,
   doctorLicense INTEGER (5) NOT NULL,
   diagnosis varchar(50) NOT NULL,
   treatment varchar(50) NOT NULL,
   startDate text NOT NULL,
   endDate text, 
   FOREIGN KEY (patientRamqCode) REFERENCES PatientFiles(ramqCode),
   FOREIGN KEY (doctorLicense) REFERENCES Doctors(license)
);

CREATE Table MedicalVisits (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   patientRamqCode varchar(12) NOT NULL,
   doctorLicense INTEGER (5) NOT NULL,
   visitDate text NOT NULL,
   diagnosis varchar(50) NOT NULL,
   treatment varchar(50) NOT NULL,
   summary text NOT NULL,
   notes text NOT NULL, 
   FOREIGN KEY (patientRamqCode) REFERENCES PatientFiles(ramqCode),
   FOREIGN KEY (doctorLicense) REFERENCES Doctors(license)
);

-- Add a medical establishment
INSERT into ContactInformation VALUES (1, 1333, "Boulevard Jacques-Cartier E", "Longueuil", "J4M2A5", "(450) 468-8111", "-");
INSERT into MedicalEstablishments VALUES (1, "Pierre-Boucher Hospital", 1);


-- Add some doctors that work in that establishment
INSERT into Users VALUES (1, "Gregory", "House", "houseMD", "aaa");
INSERT into HealthProfessionals VALUES (1, 1);
INSERT into Doctors VALUES (11111, "Internal Medicine", 1, 1);

INSERT into Users VALUES (2, "James", "Wilson", "wilsonMD", "aaa");
INSERT into HealthProfessionals VALUES (2, 2);
INSERT into Doctors VALUES (11112, "Internal Medicine", 2, 1);

INSERT into Users VALUES (3, "Perry", "Cox", "coxMD", "aaa");
INSERT into HealthProfessionals VALUES (3, 3);
INSERT into Doctors VALUES (11113, "Internal Medicine", 3, 1);


-- Add some patient files
INSERT into ContactInformation VALUES (2, 22, "Rue Ste-Catherine E", "Montreal", "H2X2M5", "(514) 888-8157", "alicealan@gmail.com");
INSERT into PatientFiles VALUES ("ALLA60050501", "Alice", "Alan", "FEMALE", "Brossard", "1960-05-05", "Father: Alex, Mother: Ally", 2);

INSERT into ContactInformation VALUES (3, 33, "Boulevard Newman", "Montreal", "H3X5S2", "(514) 333-8157", "bobbaker@gmail.com");
INSERT into PatientFiles VALUES ("BAKB68120102", "Bob", "Baker", "MALE", "Laval", "1968-12-01", "Father: Brian, Mother: Britney", 3);

INSERT into ContactInformation VALUES (4, 44, "Rue St-Joseph", "Montreal", "H2X2P4", "(514) 444-8157", "charliechaplin@gmail.com");
INSERT into PatientFiles VALUES ("CHAC70110503", "Charlie", "Chaplin", "MALE", "Montreal", "1970-11-05", "Father: Chris, Mother: Caroline", 4);


-- Add some medical histories and medical visits

-- Alice saw Dr House on July 8th and was diagnosed with a common cold. 
INSERT into MedicalHistories VALUES (1, "ALLA60050501", 11111, "Common cold", "Rest", "2022-07-08", "2022-07-08");
INSERT into MedicalVisits VALUES (1, "ALLA60050501", 11111, "2022-07-08", "Common cold", "Rest", "Patient complained of runny nose.", "Patient was asked to follow-up if symptoms persisted. ");

-- Bob saw Dr Wilson on October 10th and was diagnosed with arthrosis. 
INSERT into MedicalHistories VALUES (2, "BAKB68120102", 11112, "Knee arthrosis", "Tylenol", "2022-10-10", NULL);
INSERT into MedicalVisits VALUES (2, "BAKB68120102", 11112, "2022-10-10", "Knee arthrosis", "Tylenol", "Patient complained of painful right knee when walking up the stairs.", "Follow-up in one month.");

-- Charlie saw Dr Cox on October 12th and was diagnosed with Diabetes. 
INSERT into MedicalHistories VALUES (3, "CHAC70110503", 11113, "Diabetes", "Metformin", "2022-10-12", NULL);
INSERT into MedicalVisits VALUES (3, "CHAC70110503", 11113, "2022-10-12", "Diabetes", "Metformin", "Patient's test results indicated Diabetes.", "Started treatment. Follow-up with another test in 3 months.");


-- Test
SELECT * FROM ContactInformation WHERE id = (SELECT contactInfoId FROM PatientFiles WHERE ramqCode = "ALLA60050501");



