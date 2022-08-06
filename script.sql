DROP Table IF EXISTS MedicalHistories;
DROP Table IF EXISTS MedicalVisits;
DROP Table IF EXISTS Doctors;
DROP Table IF EXISTS MedicalEstablishments;
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
   medicalEstablishmentId INTEGER NOT NULL,
   userId INTEGER NOT NULL,
   FOREIGN KEY (medicalEstablishmentId) REFERENCES MedicalEstablishments(id),
   FOREIGN KEY (userId) REFERENCES Users(id)
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
   FOREIGN KEY (doctorLicense) REFERENCES Doctors(license),
   unique (patientRamqCode, doctorLicense, diagnosis, startDate)
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
INSERT into Doctors VALUES (11111, "Internal Medicine", 1, 1);

INSERT into Users VALUES (2, "James", "Wilson", "wilsonMD", "aaa");
INSERT into Doctors VALUES (11112, "Internal Medicine", 1, 2);

INSERT into Users VALUES (3, "Perry", "Cox", "coxMD", "aaa");
INSERT into Doctors VALUES (11113, "Internal Medicine", 1, 3);


-- Add some patient files
INSERT into ContactInformation VALUES (2, 22, "Rue Ste-Catherine E", "Montreal", "H2X2M5", "(514) 888-8157", "alicealan@gmail.com");
INSERT into PatientFiles VALUES ("ALLA60050501", "Alice", "Alan", "FEMALE", "Brossard", "1960-05-05", "Father: Alex, Mother: Ally", 2);

INSERT into ContactInformation VALUES (3, 33, "Boulevard Newman", "Montreal", "H3X5S2", "(514) 333-8157", "bobbaker@gmail.com");
INSERT into PatientFiles VALUES ("BAKB68120102", "Bob", "Baker", "MALE", "Laval", "1968-12-01", "Father: Brian, Mother: Britney", 3);

INSERT into ContactInformation VALUES (4, 44, "Rue St-Joseph", "Montreal", "H2X2P4", "(514) 444-8157", "charliechaplin@gmail.com");
INSERT into PatientFiles VALUES ("CHAC70110503", "Charlie", "Chaplin", "MALE", "Montreal", "1970-11-05", "Father: Chris, Mother: Caroline", 4);


-- Add some medical histories and medical visits

-- Medical history for Alice
INSERT into MedicalHistories VALUES (1, "ALLA60050501", 11112, "Childhood ADHD", "Ritalin", "1965-05-05", "1970-05-05");
INSERT into MedicalHistories VALUES (2, "ALLA60050501", 11111, "Hypertension", "ACE inhibitor", "2016-01-11", NULL);
INSERT into MedicalHistories VALUES (3, "ALLA60050501", 11113, "Covid-19", "Isolation and Rest", "2022-05-08", "2022-05-22");
INSERT into MedicalHistories VALUES (4, "ALLA60050501", 11111, "Benign breast tumor", "Regular follow-up", "2022-06-11", NULL);
INSERT into MedicalHistories VALUES (5, "ALLA60050501", 11111, "Common cold", "Rest", "2022-07-08", "2022-07-10");

-- Alice's medical visits
INSERT into MedicalVisits VALUES (1, "ALLA60050501", 11111, "2016-01-11", "Hypertension", "Ace inhibitor", "Patient presented 160/90. Treatment prescribed.", "Follow-up as needed.");
INSERT into MedicalVisits VALUES (2, "ALLA60050501", 11113, "2022-05-08", "Covid-19", "Isolation and Rest", "Patient presented cough and fever, quick test was positive.", "Patient advised to go to ER if short of breath.");
INSERT into MedicalVisits VALUES (3, "ALLA60050501", 11111, "2022-07-08", "Common cold", "Rest", "Patient complained of runny nose.", "Patient was asked to follow-up if symptoms persisted. ");


-- Medical history for Bob
INSERT into MedicalHistories VALUES (6, "BAKB68120102", 11112, "Broken arm", "Surgery", "2019-01-25", "2019-05-25");
INSERT into MedicalHistories VALUES (7, "BAKB68120102", 11112, "Knee arthrosis", "Tylenol", "2022-08-01", NULL);

-- Bob's medical visits
INSERT into MedicalVisits VALUES (4, "BAKB68120102", 11112, "2019-01-25", "Broken arm", "Surgery", "Patient fell on right arm and can't feel his fingers.", "Radiograph showed a severe fracture, patient was sent for immediate surgery.");
INSERT into MedicalVisits VALUES (5, "BAKB68120102", 11112, "2022-08-01", "Knee arthrosis", "Tylenol", "Patient complained of painful right knee when walking up the stairs.", "Follow-up in one month.");


-- Medical history for Charlie
INSERT into MedicalHistories VALUES (8, "CHAC70110503", 11113, "Diabetes", "Metformin", "2022-07-12", NULL);
INSERT into MedicalHistories VALUES (9, "CHAC70110503", 11113, "Hypertension", "Exercise", "2022-07-12", NULL);
INSERT into MedicalHistories VALUES (10, "CHAC70110503", 11113, "High cholesterol", "Exercise", "2022-07-12", NULL);

-- Charlie's medical visits.
INSERT into MedicalVisits VALUES (6, "CHAC70110503", 11113, "2022-07-12", "Diabetes, hypertension, cholesterol", "Metformin and exercise", "Patient's test results indicated Diabetes, hypertension and cholesterol. Started treatment.", "Patient says he will do exercise but I am not convinced. Follow-up in 3 months.");