-- Insertar países (solo si no existen)
INSERT INTO country (name)
SELECT 'DOMINICAN REPUBLIC'
WHERE NOT EXISTS (
    SELECT 1 FROM country WHERE UPPER(name) = 'DOMINICAN REPUBLIC'
);

INSERT INTO country (name)
SELECT 'UNITED STATES'
WHERE NOT EXISTS (
    SELECT 1 FROM country WHERE UPPER(name) = 'UNITED STATES'
);

-- Insertar ciudades (verifica primero si el país existe)
INSERT INTO city (name, country_id)
SELECT 'SANTO DOMINGO', c.id
FROM country c
WHERE c.name = 'DOMINICAN REPUBLIC'
  AND NOT EXISTS (
    SELECT 1 FROM city WHERE name = 'SANTO DOMINGO' AND country_id = c.id
);

INSERT INTO city (name, country_id)
SELECT 'SANTIAGO', c.id
FROM country c
WHERE c.name = 'DOMINICAN REPUBLIC'
  AND NOT EXISTS (
    SELECT 1 FROM city WHERE name = 'SANTIAGO' AND country_id = c.id
);

INSERT INTO city (name, country_id)
SELECT 'MIAMI', c.id
FROM country c
WHERE c.name = 'UNITED STATES'
  AND NOT EXISTS (
    SELECT 1 FROM city WHERE name = 'MIAMI' AND country_id = c.id
);
