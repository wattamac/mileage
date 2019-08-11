CREATE TABLE Mileage (
           userId VARCHAR(20) NOT NULL PRIMARY KEY,
           point NUMBER(10) NOT NULL,
           lastUpdatedAt TIMESTAMP NOT NULL
);

CREATE TABLE MileageEventHistory (
    id NUMBER(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    reviewId VARCHAR(20) NOT NULL,
    placeId VARCHAR(20) NOT NULL,
    ruleType VARCHAR(10) NOT NULL,
    point NUMBER(10) NOT NULL,
    action VARCHAR(10) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    userId VARCHAR(20) NOT NULL
)

CREATE INDEX IDX1_MILEAGE_EVENT_HISTORY ON MileageEventHistory
(reviewId, ruleType);

CREATE INDEX IDX2_MILEAGE_EVENT_HISTORY ON MileageEventHistory
(placeId);