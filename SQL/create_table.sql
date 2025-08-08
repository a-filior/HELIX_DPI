CREATE TABLE IF NOT EXISTS PATIENT (
  pat_id int NOT NULL,
  pat_nom_usuel varchar(256),
  pat_nom_naissance varchar(256),
  pat_nom_prenom varchar(256),
  pate_date_naissance date,
  pat_sexe enum('M','F','U'),
  pat_date_deces date DEFAULT NULL,
  pat_cp_naissance varchar(8),
  pat_nationalite_insee varchar(32),
  PRIMARY KEY (pat_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS PATIENT_IDENTIFIANT (
  pidt_id int NOT NULL,
  pat_id int NOT NULL,
  pidt_type_id varchar(256),
  pidt_valeur varchar(256),
  pidt_authorite varchar(256),
  PRIMARY KEY (pidt_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS VENUE (
  ven_id int NOT NULL,
  pat_id int NOT NULL,
  ven_numero varchar(256),
  ven_type int,
  ven_date_debut varchar(256),
  ven_date_fin varchar(256),
  PRIMARY KEY (ven_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS ADRESSE (
  adr_id int NOT NULL,
  adr_libelle1 varchar(256),
  adr_libelle2 varchar(256),
  adr_ville varchar(256),
  adr_cp varchar(256),
  adr_pays varchar(128),
  adr_iso2 varchar(128),
  adr_iso3 varchar(128),
  adr_type int,
  PRIMARY KEY (adr_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS PATIENT_ADRESSE (
  adr_id int NOT NULL,
  pat_id int NOT NULL,
  PRIMARY KEY (adr_id, pat_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE IF NOT EXISTS ROLE (
  rol_id int NOT NULL,
  rol_libelle varchar(128) NOT NULL,
  PRIMARY KEY (rol_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS INTERVENANT (
  int_id int NOT NULL,
  int_matricule varchar(64) NOT NULL,
  int_nom varchar(256),
  int_prenom varchar(256),
  rol_id varchar(256),
  PRIMARY KEY (int_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS IDENTIFIANTS_CONNEXION (
  iden_id int NOT NULL,
  int_matricule varchar(64) NOT NULL,
  iden_login varchar(128) NOT NULL,
  iden_password varchar(128) NOT NULL,
  PRIMARY KEY (iden_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


