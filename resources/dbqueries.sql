-- name: login-user
SELECT *
FROM korisnik
WHERE username = :username and  password = :password


-- name: signin-user!
INSERT INTO korisnik
 ( username, password, imePrezime)
 VALUES (:username, :password, :imePrezime )


-- name: proveriusername
SELECT *
FROM korisnik
WHERE username = :username


-- name: vrati-filmove
SELECT film.filmID, film.imeFilma, film.godinaSnimanja, drzava.nazivDrzave, film.username
FROM film JOIN drzava ON film.drzavaID=drzava.drzavaID

-- name: vrati-film-premaid
SELECT film.filmID, film.imeFilma, film.godinaSnimanja, drzava.nazivDrzave, film.username, film.kratakOpis
FROM film JOIN drzava ON film.drzavaID=drzava.drzavaID
where filmID = :filmID


-- name: obrisi-film!
DELETE
FROM film
WHERE filmID = :filmID


-- name: film-new!
INSERT INTO film
 (imeFilma, kratakOpis, godinaSnimanja, drzavaID, username)
 VALUES (:imeFilma, :kratakOpis, :godinaSnimanja, :drzavaID, :username)


-- name: film-edit!
UPDATE film
SET imeFilma = :imeFilma, kratakOpis = :kratakOpis, godinaSnimanja = :godinaSnimanja, drzavaID = :drzavaID, username = :username
WHERE filmID = :filmID


-- name: vrati-nazive-drzava
SELECT nazivDrzave
from  drzava


-- name: vrati-id-drzave
SELECT drzavaID
from  drzava
where nazivDrzave = :nazivDrzave

-- name: vrati-id-filma
SELECT filmID
from  film
where imeFilma = :imeFilma



-- name: pretraga-glumaca
SELECT glumac.glumacID, glumac.imePrezime, glumac.godinaRodjenja, glumac.pol, drzava.nazivDrzave
from  glumac join drzava on glumac.drzavaID=drzava.drzavaID
where imePrezime = :imePrezime

-- name: vrati-glumce
SELECT glumac.glumacID, glumac.imePrezime, glumac.godinaRodjenja, glumac.pol, drzava.nazivDrzave
from  glumac join drzava on glumac.drzavaID=drzava.drzavaID



-- name: vrati-glumce-kojiglumeufilmu
SELECT glumac.glumacID, glumac.imePrezime, glumac.godinaRodjenja, glumac.pol, drzava.nazivDrzave
from  glumac join drzava on glumac.drzavaID=drzava.drzavaID join glumacfilm on glumac.glumacID=glumacfilm.glumacID
where glumacfilm.filmID = :filmID

-- name: glumacfilm-new!
INSERT INTO glumacfilm
 (filmID, glumacID)
 VALUES (:filmID, :glumacID)

-- name: glumacfilm-remove!
DELETE FROM glumacfilm
WHERE glumacID = :glumacID AND filmID = :filmID

-- name: glumac-delete!
DELETE
FROM glumac
WHERE glumacID = :glumacID

-- name: glumac-new!
INSERT INTO glumac
 (imePrezime, godinaRodjenja, pol, drzavaID)
 VALUES (:imePrezime, :godinaRodjenja, :pol, :drzavaID)

-- name: vrati-polove
SELECT *
from  pol

-- name: glumac-edit!
UPDATE glumac
SET imePrezime = :imePrezime, godinaRodjenja = :godinaRodjenja, pol = :pol, drzavaID = :drzavaID
WHERE glumacID = :glumacID

-- name: vrati-glumca-premaid
SELECT glumac.glumacID, glumac.imePrezime, glumac.godinaRodjenja, glumac.pol, drzava.nazivDrzave
from  glumac join drzava on glumac.drzavaID=drzava.drzavaID
where glumac.glumacID = :glumacID
