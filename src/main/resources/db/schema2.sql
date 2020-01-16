CREATE TABLE GROUPDETAIL (
      ID BIGINT NOT NULL,
      CHN VARCHAR NOT NULL,
      LIBRARYNAME1 VARCHAR(50) NOT NULL,
      LIBRARYNAME2 VARCHAR(50) NOT NULL,
      FEATUREID VARCHAR(50) NOT NULL,
      PEOPLEID VARCHAR(50) NOT NULL,
      CATEGORY VARCHAR(50) NOT NULL,
      X INT NOT NULL ,
      Y INT NOT NULL ,
      UPDATEDON BIGINT NOT NULL,
      BIOSUBTYPE INT NOT NULL,
      PRIMARY KEY (ID)
);

CREATE UNIQUE INDEX FEATURE ON GROUPDETAIL(CHN, LIBRARYNAME1, LIBRARYNAME2, FEATUREID);
CREATE INDEX LIBRARYNAME ON GROUPDETAIL(CHN, LIBRARYNAME1, LIBRARYNAME2);
CREATE INDEX PEOPLE ON GROUPDETAIL(PEOPLEID,CATEGORY);
