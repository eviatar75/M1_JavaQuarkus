INSERT INTO houri_db.Bien_immobilier (id, amiante, chauffage, code_postal, date_construction, etage, indice_perf_energetique, isolation, nb_piece, numero_rue, porte, rue, statut_hypoteque, superficie) VALUES (1, false, 'fioul', '92140', '2021-11-25', 2, 'A', true, 5, '6', '4004', 'rue de la richesse', false, 56);
INSERT INTO houri_db.Bien_immobilier (id, amiante, chauffage, code_postal, date_construction, etage, indice_perf_energetique, isolation, nb_piece, numero_rue, porte, rue, statut_hypoteque, superficie) VALUES (2, false, 'bois', '75019', '2002-07-11', 4, 'C', true, 3, '90', '8', 'rue tolbiac', false, 80);
INSERT INTO houri_db.Bien_immobilier (id, amiante, chauffage, code_postal, date_construction, etage, indice_perf_energetique, isolation, nb_piece, numero_rue, porte, rue, statut_hypoteque, superficie) VALUES (3, false, 'gaz', '77000', '2002-07-11', 4, 'B', true, 3, '14', '8', 'avenue montaigne', true, 80);
INSERT INTO houri_db.Bien_immobilier (id, amiante, chauffage, code_postal, date_construction, etage, indice_perf_energetique, isolation, nb_piece, numero_rue, porte, rue, statut_hypoteque, superficie) VALUES (4, false, 'bois', '75016', '2002-07-11', 4, 'D', true, 3, '55', '8', 'place de la concorde', true, 80);

INSERT INTO houri_db.Personne (num_securite_social, code_postal, date_naissance, lieu_naissance, mail, nom, numero_rue, prenom, rue, statut_maritale, telephone) VALUES ('1', '75019', '2021-12-15', 'Paris', '20sur20@gloire.satisfaction', 'houri', '27', 'Eviatar', 'villa curial', 'celibataire', '0140185218');
INSERT INTO houri_db.Personne (num_securite_social, code_postal, date_naissance, lieu_naissance, mail, nom, numero_rue, prenom, rue, statut_maritale, telephone) VALUES ('2', '93500', '2021-12-07', 'Marseille', 'lewis@hamilton.gp', 'hamilton', '1', 'Lewis', 'victory', 'celibataire', '1111111111');
INSERT INTO houri_db.Personne (num_securite_social, code_postal, date_naissance, lieu_naissance, mail, nom, numero_rue, prenom, rue, statut_maritale, telephone) VALUES ('2535339325469', '94500', '2002-10-02', 'Namek', 'Complot@chloroquine.pfizer', 'Guennif', '3', 'Oualid', 'Avenue Bart Simpson', 'celibataire', '0000000000');
INSERT INTO houri_db.Personne (num_securite_social, code_postal, date_naissance, lieu_naissance, mail, nom, numero_rue, prenom, rue, statut_maritale, telephone) VALUES ('31765522266997', '95200', '2002-08-08', 'Zarzis', 'StopTheCount@Trump.us', 'Mrenda', '6', 'Castaldinho', '24 Allée Léon Paul Fargue', 'celibataire', '5555555555');

INSERT INTO houri_db.Transaction (id, date_compromis_vente, date_debut, date_fin, prix_vente, acheteur, bien_id, vendeur) VALUES (1, '1975-06-19', '1975-09-19', '2006-11-15', 500000, '1', 1, null);
INSERT INTO houri_db.Transaction (id, date_compromis_vente, date_debut, date_fin, prix_vente, acheteur, bien_id, vendeur) VALUES (2, '2006-08-15', '2006-11-15', null, 700000, '2', 1, '1');
INSERT INTO houri_db.Transaction (id, date_compromis_vente, date_debut, date_fin, prix_vente, acheteur, bien_id, vendeur) VALUES (3, '1975-06-19', '1975-09-19', '2006-11-15', 500000, '1', 3, null);
INSERT INTO houri_db.Transaction (id, date_compromis_vente, date_debut, date_fin, prix_vente, acheteur, bien_id, vendeur) VALUES (4, '1975-06-19', '1975-09-19', null, 500000, '2', 3, '1');

