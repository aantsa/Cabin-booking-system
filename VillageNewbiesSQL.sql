
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asiakas`
--

DROP TABLE IF EXISTS `asiakas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asiakas` (
  `asiakas_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `postinro` char(5) NOT NULL,
  `etunimi` varchar(20) DEFAULT NULL,
  `sukunimi` varchar(40) DEFAULT NULL,
  `lahiosoite` varchar(40) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `puhelinnro` varchar(15) DEFAULT NULL,
  `hetu/Ytunnus` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`asiakas_id`),
  KEY `fk_as_posti1_idx` (`postinro`),
  KEY `asiakas_snimi_idx` (`sukunimi`),
  KEY `asiakas_enimi_idx` (`etunimi`),
  CONSTRAINT `fk_asiakas_posti` FOREIGN KEY (`postinro`) REFERENCES `posti` (`postinro`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asiakas`
--

LOCK TABLES `asiakas` WRITE;
/*!40000 ALTER TABLE `asiakas` DISABLE KEYS */;
INSERT INTO `asiakas` VALUES (1,'01640','Oskari','Kukkanen','Postitie 23','oskari.kukkanen@gmail.com','+358502383742','010189-237X'),(2,'70110','Mika','Kivi','Puukatu 5','mika.kivi@gmail.com','+358400382582','030596-427V');
/*!40000 ALTER TABLE `asiakas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lasku`
--

DROP TABLE IF EXISTS `lasku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lasku` (
  `lasku_id` int(11) NOT NULL,
  `varaus_id` int(11) unsigned NOT NULL,
  `summa` double(8,2) NOT NULL,
  `alv` double(8,2) NOT NULL,
  PRIMARY KEY (`lasku_id`),
  KEY `lasku_ibfk_1` (`varaus_id`),
  CONSTRAINT `lasku_ibfk_1` FOREIGN KEY (`varaus_id`) REFERENCES `varaus` (`varaus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lasku`
--

LOCK TABLES `lasku` WRITE;
/*!40000 ALTER TABLE `lasku` DISABLE KEYS */;
INSERT INTO `lasku` VALUES (1,1,1010.00,195.48),(2,2,1060.00,205.16);
/*!40000 ALTER TABLE `lasku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mokinomistaja`
--

DROP TABLE IF EXISTS `mokinomistaja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mokinomistaja` (
  `idmokinOmistaja` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `etunimi` varchar(20) NOT NULL,
  `sukunimi` varchar(30) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `lahiosoite` varchar(45) DEFAULT NULL,
  `puhelinnumero` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idmokinOmistaja`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mokinomistaja`
--

LOCK TABLES `mokinomistaja` WRITE;
/*!40000 ALTER TABLE `mokinomistaja` DISABLE KEYS */;
INSERT INTO `mokinomistaja` VALUES (1,'Pentti','Sauramo','pentti.sauramo@gmail.com','Lasipolku 12','+358400382574'),(2,'Niina','Kivikko','niina.kivikko@gmail.com','Kukkatie 1','+358400382392');
/*!40000 ALTER TABLE `mokinomistaja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mokki`
--

DROP TABLE IF EXISTS `mokki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mokki` (
  `mokki_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `toimintaalue_id` int(11) unsigned NOT NULL,
  `postinro` char(5) NOT NULL,
  `mokkinimi` varchar(45) DEFAULT NULL,
  `katuosoite` varchar(45) DEFAULT NULL,
  `kuvaus` varchar(150) DEFAULT NULL,
  `henkilomaara` int(11) DEFAULT NULL,
  `mokinOmistaja_idmokinOmistaja` int(10) unsigned NOT NULL,
  `hinta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`mokki_id`),
  KEY `fk_mokki_alue_idx` (`toimintaalue_id`),
  KEY `fk_mokki_posti_idx` (`postinro`),
  KEY `fk_mokki_mokinOmistaja1_idx` (`mokinOmistaja_idmokinOmistaja`),
  CONSTRAINT `fk_mokki_mokinOmistaja1` FOREIGN KEY (`mokinOmistaja_idmokinOmistaja`) REFERENCES `mokinomistaja` (`idmokinOmistaja`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mokki_posti` FOREIGN KEY (`postinro`) REFERENCES `posti` (`postinro`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mokki_toimintaalue` FOREIGN KEY (`toimintaalue_id`) REFERENCES `toimintaalue` (`toimintaalue_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mokki`
--

LOCK TABLES `mokki` WRITE;
/*!40000 ALTER TABLE `mokki` DISABLE KEYS */;
INSERT INTO `mokki` VALUES (1,1,'01640','Nygord','Nygordinkatu 1','Mökki upealla näköalalla',10,1,'1000'),(2,2,'70110','Loiste','Loistetie 14','Mökki isolla pihalla',8,2,'1200');
/*!40000 ALTER TABLE `mokki` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mokkikohtainenvarustelu`
--

DROP TABLE IF EXISTS `mokkikohtainenvarustelu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mokkikohtainenvarustelu` (
  `varustelu_idvarustelu` int(10) unsigned NOT NULL,
  `mokki_mokki_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`varustelu_idvarustelu`,`mokki_mokki_id`),
  KEY `fk_varustelu_has_mokki_mokki1_idx` (`mokki_mokki_id`),
  KEY `fk_varustelu_has_mokki_varustelu1_idx` (`varustelu_idvarustelu`),
  CONSTRAINT `fk_varustelu_has_mokki_mokki1` FOREIGN KEY (`mokki_mokki_id`) REFERENCES `mokki` (`mokki_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_varustelu_has_mokki_varustelu1` FOREIGN KEY (`varustelu_idvarustelu`) REFERENCES `varustelu` (`idvarustelu`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mokkikohtainenvarustelu`
--

LOCK TABLES `mokkikohtainenvarustelu` WRITE;
/*!40000 ALTER TABLE `mokkikohtainenvarustelu` DISABLE KEYS */;
INSERT INTO `mokkikohtainenvarustelu` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `mokkikohtainenvarustelu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `palvelu`
--

DROP TABLE IF EXISTS `palvelu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `palvelu` (
  `palvelu_id` int(11) unsigned NOT NULL,
  `toimintaalue_id` int(11) unsigned NOT NULL,
  `nimi` varchar(40) DEFAULT NULL,
  `tyyppi` int(11) DEFAULT NULL,
  `kuvaus` varchar(255) DEFAULT NULL,
  `hinta` double(8,2) NOT NULL,
  `alv` double(8,2) NOT NULL,
  PRIMARY KEY (`palvelu_id`),
  KEY `Palvelu_nimi_index` (`nimi`),
  KEY `palv_toimip_id_ind` (`toimintaalue_id`),
  CONSTRAINT `palvelu_ibfk_1` FOREIGN KEY (`toimintaalue_id`) REFERENCES `toimintaalue` (`toimintaalue_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `palvelu`
--

LOCK TABLES `palvelu` WRITE;
/*!40000 ALTER TABLE `palvelu` DISABLE KEYS */;
INSERT INTO `palvelu` VALUES (1,1,'Aamupala',1,'Aamupala tarjoillaa klo. 10-12',10.00,1.93),(2,1,'Hieronta',1,'Hierontaa kylpylässä klo. 10-20 välillä',60.00,11.61);
/*!40000 ALTER TABLE `palvelu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posti`
--

DROP TABLE IF EXISTS `posti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posti` (
  `postinro` char(5) NOT NULL,
  `toimipaikka` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`postinro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posti`
--

LOCK TABLES `posti` WRITE;
/*!40000 ALTER TABLE `posti` DISABLE KEYS */;
INSERT INTO `posti` VALUES ('01640','Helsinki'),('70110','Kuopio');
/*!40000 ALTER TABLE `posti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toimintaalue`
--

DROP TABLE IF EXISTS `toimintaalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `toimintaalue` (
  `toimintaalue_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nimi` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`toimintaalue_id`),
  KEY `Toimintaalue_nimi_index` (`nimi`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toimintaalue`
--

LOCK TABLES `toimintaalue` WRITE;
/*!40000 ALTER TABLE `toimintaalue` DISABLE KEYS */;
INSERT INTO `toimintaalue` VALUES (2,'Lappi'),(1,'Uusimaa');
/*!40000 ALTER TABLE `toimintaalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `varauksen_palvelut`
--

DROP TABLE IF EXISTS `varauksen_palvelut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `varauksen_palvelut` (
  `varaus_id` int(11) unsigned NOT NULL,
  `palvelu_id` int(11) unsigned NOT NULL,
  `lkm` int(11) NOT NULL,
  PRIMARY KEY (`palvelu_id`,`varaus_id`),
  KEY `vp_varaus_id_index` (`varaus_id`),
  KEY `vp_palvelu_id_index` (`palvelu_id`),
  CONSTRAINT `fk_palvelu` FOREIGN KEY (`palvelu_id`) REFERENCES `palvelu` (`palvelu_id`),
  CONSTRAINT `fk_varaus` FOREIGN KEY (`varaus_id`) REFERENCES `varaus` (`varaus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `varauksen_palvelut`
--

LOCK TABLES `varauksen_palvelut` WRITE;
/*!40000 ALTER TABLE `varauksen_palvelut` DISABLE KEYS */;
INSERT INTO `varauksen_palvelut` VALUES (1,1,1),(2,2,1);
/*!40000 ALTER TABLE `varauksen_palvelut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `varaus`
--

DROP TABLE IF EXISTS `varaus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `varaus` (
  `varaus_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `asiakas_id` int(11) unsigned NOT NULL,
  `mokki_mokki_id` int(10) unsigned NOT NULL,
  `varattu_pvm` datetime DEFAULT NULL,
  `vahvistus_pvm` datetime DEFAULT NULL,
  `varattu_alkupvm` datetime DEFAULT NULL,
  `varattu_loppupvm` datetime DEFAULT NULL,
  PRIMARY KEY (`varaus_id`),
  KEY `varaus_as_id_index` (`asiakas_id`),
  KEY `fk_var_mok_idx` (`mokki_mokki_id`),
  CONSTRAINT `fk_varaus_mokki` FOREIGN KEY (`mokki_mokki_id`) REFERENCES `mokki` (`mokki_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `varaus_ibfk` FOREIGN KEY (`asiakas_id`) REFERENCES `asiakas` (`asiakas_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `varaus`
--

LOCK TABLES `varaus` WRITE;
/*!40000 ALTER TABLE `varaus` DISABLE KEYS */;
INSERT INTO `varaus` VALUES (1,1,1,'2020-02-10 00:00:00','2020-03-20 00:00:00','2020-04-10 00:00:00','2020-04-16 00:00:00'),(2,2,2,'2020-03-15 00:00:00','2020-04-21 00:00:00','2020-05-16 00:00:00','2020-05-22 00:00:00');
/*!40000 ALTER TABLE `varaus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `varustelu`
--

DROP TABLE IF EXISTS `varustelu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `varustelu` (
  `idvarustelu` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nimi` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idvarustelu`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `varustelu`
--

LOCK TABLES `varustelu` WRITE;
/*!40000 ALTER TABLE `varustelu` DISABLE KEYS */;
INSERT INTO `varustelu` VALUES (1,'Mönkijä'),(2,'Purjevene');
/*!40000 ALTER TABLE `varustelu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-24 12:24:51
SELECT * FROM asiakas;
SELECT * FROM mokki;