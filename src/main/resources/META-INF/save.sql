;             
CREATE USER IF NOT EXISTS WEBUSER SALT 'ea68f9ca2b43db60' HASH '95b6458582333cafe1bf417aa8518ee9b0c8b44398a047e1af79c74028911d0f';            
CREATE USER IF NOT EXISTS SA SALT '2f82d319801fa110' HASH 'a9aa00bfa69ce946a5efe1a94b281521e4714cdd5852eefa9a16aa4ffe49ffd2' ADMIN;           
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_5B8A0F89_FD44_41B8_BC94_7CE66F260835 START WITH 1 BELONGS_TO_TABLE;    
CREATE SEQUENCE PUBLIC.HIBERNATE_SEQUENCE START WITH 33;      
CREATE CACHED TABLE PUBLIC.ORDERED(
    ID_ORDER INT NOT NULL,
    ITEM_ID VARCHAR(255) NOT NULL,
    UTILISATEUR_ID VARCHAR(255) NOT NULL,
    QUANTITE INT,
    NUMERO_COMMANDE VARCHAR(255)
);       
ALTER TABLE PUBLIC.ORDERED ADD CONSTRAINT PUBLIC.CONSTRAINT_E PRIMARY KEY(ID_ORDER);          
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.ORDERED;      
CREATE CACHED TABLE PUBLIC.ITEM(
    CODE VARCHAR(255) NOT NULL,
    COST DOUBLE,
    IMAGEURL VARCHAR(255),
    NAME VARCHAR(255),
    QUANTITY INTEGER
);             
ALTER TABLE PUBLIC.ITEM ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(CODE); 
-- 10 +/- SELECT COUNT(*) FROM PUBLIC.ITEM;   
INSERT INTO PUBLIC.ITEM(CODE, COST, IMAGEURL, NAME, QUANTITY) VALUES
('M1', 25.0, 'https://www.boursot.fr/media/catalog/product/cache/1/image/900x900/fb784b724faaeb2b9ac87b2dc2aea145/c/h/charmes_de_kirwan_ch_teau_de_kirwan_second_vin_margaux_vin_rouge_bordeaux_vin_rouge_margaux_boursot_vins_pas_de_calais_3.jpg', 'Chateau margaux', 10),
('G1', 23.0, 'http://medias.nicolas.com/media/sys_master/images/hd4/h1f/8892282011678.png', 'Graves', 15),
('CC1', 17.0, 'https://i2.cdscdn.com/pdt2/x/1/4/1/300x300/chchivbdx14/rw/chateau-chivadeau-2014-vin-de-bordeaux-rouge-3.jpg', 'Chateau chivadeau', 20),
('HB1', 17.0, 'http://cave.pheeric.com/wp-content/uploads/2016/03/haut_bazignan_510x600.jpg', 'Haut bazignan', 30),
('MG2', 14.0, 'https://www.carrefourmarket-groupemestdagh.be/220-thickbox_default/manoir-de-galhaud-grand-vin-bordeaux.jpg', 'Manoir galhaud', 10),
('K1', 16.0, 'http://www.kressmann.com/_kressmann/_mediatheque/fichiers/Bordeaux_rouge_Kressmann_Grande_Reserve-Kressmann_vins_Bordeaux.png', 'Kressman', 5),
('MO1', 15.0, 'http://www.kressmann.com/_kressmann/_mediatheque/fichiers/Bordeaux_Kressmann_Monopole-Kressmann_vins_Bordeaux.png', 'Monopole', 30),
('B1', 20.0, 'http://www.aries-vins.com/2450-3711-home/chateau-boutisse-2011.jpg', 'Chateau boutisse', 10),
('CB1', 12.0, 'https://www.vinatis.com/27300-large_default/chateau-du-breuil-anjou-rouge-2015.png', 'Chateau du breuil', 20),
('SP1', 18.0, 'https://www.cave-bruant.fr/3279-large_default/domaine-saint-pierre-seigneur-de-la-noe-vieilles-vignes-anjou.jpg', 'Saint pierre', 25);      
CREATE CACHED TABLE PUBLIC.UTILISATEUR(
    MAIL VARCHAR(100) NOT NULL,
    MDP VARCHAR(100) NOT NULL,
    NOM VARCHAR(50) NOT NULL,
    PRENOM VARCHAR(50) NOT NULL,
    CODEPOSTAL INT NOT NULL,
    VILLE VARCHAR(40) NOT NULL,
    PAYS VARCHAR(30) NOT NULL,
    ADRESSE VARCHAR(100) NOT NULL,
    ISADMIN BOOLEAN
);         
ALTER TABLE PUBLIC.UTILISATEUR ADD CONSTRAINT PUBLIC.CONSTRAINT_5 PRIMARY KEY(MAIL);          
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.UTILISATEUR;             
INSERT INTO PUBLIC.UTILISATEUR(MAIL, MDP, NOM, PRENOM, CODEPOSTAL, VILLE, PAYS, ADRESSE, ISADMIN) VALUES
('admin@mail.fr', 'C93CCD78B2076528346216B3B2F701E6', 'Admin', 'Admin', 44000, 'Nantes', 'France', '5 Rue Alfred Kestler', TRUE);        
ALTER TABLE PUBLIC.ORDERED ADD CONSTRAINT PUBLIC.FKFQL1I5SNJVS5TKTT375KKS07 FOREIGN KEY(UTILISATEUR_ID) REFERENCES PUBLIC.UTILISATEUR(MAIL) NOCHECK;          
ALTER TABLE PUBLIC.ORDERED ADD CONSTRAINT PUBLIC.FKBP6PR197S7IS1OXQEB2ME4MCV FOREIGN KEY(ITEM_ID) REFERENCES PUBLIC.ITEM(CODE) NOCHECK;       
ALTER TABLE PUBLIC.ORDERED ADD CONSTRAINT PUBLIC.ITEM_FK FOREIGN KEY(ITEM_ID) REFERENCES PUBLIC.ITEM(CODE) NOCHECK;           
ALTER TABLE PUBLIC.ORDERED ADD CONSTRAINT PUBLIC.USER_FK FOREIGN KEY(UTILISATEUR_ID) REFERENCES PUBLIC.UTILISATEUR(MAIL) NOCHECK;             
