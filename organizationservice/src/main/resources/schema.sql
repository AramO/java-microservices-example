DROP TABLE IF EXISTS ar_organization;

CREATE TABLE ar_organization (
  id                     SERIAL PRIMARY KEY NOT NULL,
  org_name               VARCHAR(100) NOT NULL,
  contact_name           VARCHAR(100) NOT NULL,
  contact_email          VARCHAR(100) NOT NULL,
  contact_phone          VARCHAR(100) NOT NULL
);

