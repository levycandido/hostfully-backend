-- Insert initial data into the `place` table
INSERT INTO place (id, name, street, latitude, longitude) VALUES
 (1, 'D.O.M.', 'Rua Barão de Capanema, 549', -23.564480, -46.658820),
 (2, 'Maní', 'Rua Joaquim Antunes, 210', -23.561399, -46.676544),
 (3, 'Figueira Rubaiyat', 'Rua Haddock Lobo, 1738', -23.564004, -46.668647),
 (4, 'A Casa do Porco', 'Rua Araújo, 124', -23.543950, -46.643207),
 (5, 'Tordesilhas', 'Alameda Tietê, 489', -23.563301, -46.667766),
 (6, 'Evvai', 'Rua Joaquim Antunes, 108', -23.561511, -46.676437),
 (7, 'Osteria Francescana', 'Rua Oscar Freire, 45', -23.563092, -46.671801),
 (8, 'Jun Sakamoto', 'Rua Lisboa, 55', -23.557332, -46.674231),
 (9, 'Kinoshita', 'Rua Jacques Félix, 415', -23.583538, -46.674902),
 (10, 'Sassá Sushi', 'Rua Horácio Lafer, 634', -23.578744, -46.676947);

ALTER TABLE PLACE ALTER COLUMN ID RESTART WITH 11;


