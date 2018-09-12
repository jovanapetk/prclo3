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

