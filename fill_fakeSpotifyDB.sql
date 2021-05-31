USE fake_spotifyDB;

INSERT INTO Users(username) VALUES
('user1'),
('user2'),
('user3'),
('user4'),
('userr5'),
('user6'),
('user7'),
('user8'),
('user9'),
('user10');

INSERT INTO Musicians(musician_name) VALUES
('Megadeth'),
('Dead Kennedys'),
('Anthrax'),
('Judas Priest'),
('Black Sabbath'),
('Slayer'),
('Testament'),
('Motorhead'),
('Overkill'),
('Death Angel'),
('Dio'),
('Exodus'),
('Ghost'),
('Type O Negative'),
('Metallica'),
('Annihilator'),
('Sepultura'),
('Pantera'),
('Spyair'),
('Green Day'),
('Evanescence'),
('Asian Kung-fu Generation'),
('Sunset Rollercoaster'),
('The Ramones'),
('Sumika');

INSERT INTO listens_to(userid, musician_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 13),
(1, 25),
(1, 21),
(1, 22),

(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 9),
(2, 10),
(2, 11),
(2, 12),
(2, 18),
(2, 15),
(2, 14),

(3, 1),
(3, 2),
(3, 3),
(3, 11),
(3, 12),
(3, 10),
(3, 17),
(3, 18),
(3, 24),
(3, 20),

(4, 2),
(4, 8),
(4, 20),
(4, 24),
(4, 5),

(5, 20),
(5, 24),
(5, 25),
(5, 23),
(5, 22),
(5, 21),

(6, 4),
(6, 8),
(6, 11),
(6, 13),
(6, 5);

SELECT * FROM Musicians;

SELECT l.userid, m.musician_name FROM listens_to l
JOIN Musicians m ON m.musician_id = l.musician_id
ORDER BY userid;

SELECT m.musician_name, COUNT(*) AS num_occ FROM listens_to l
JOIN Musicians m ON m.musician_id = l.musician_id
WHERE l.userid IN(
	SELECT l.userid FROM listens_to l
	JOIN Musicians m ON m.musician_id = l.musician_id
	WHERE m.musician_name = 'Megadeth')
GROUP BY m.musician_name
ORDER BY num_occ DESC;

SELECT m.musician_name FROM listens_to l
JOIN Musicians m ON m.musician_id = l.musician_id
WHERE l.userid IN(
	SELECT l.userid FROM listens_to l
	JOIN Musicians m ON m.musician_id = l.musician_id
	WHERE m.musician_name = 'Megadeth')
GROUP BY m.musician_name
ORDER BY COUNT(*) DESC;
