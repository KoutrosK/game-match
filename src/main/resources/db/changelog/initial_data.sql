insert into match(description, match_date, match_time, team_a, team_b, sport)
values ('OSFP-PAO', '2023-03-31', '12:00', 'OSFP', 'PAO', 1);

insert into match_odds(match_id, specifier, odd) values (1, 'X', 0.75);
insert into match_odds(match_id, specifier, odd) values (1, '1', 1.50);
insert into match_odds(match_id, specifier, odd) values (1, '2', 0.35);

insert into match(description, match_date, match_time, team_a, team_b, sport)
values ('AEK-PAO', '2023-05-28', '15:00', 'AEK', 'PAO', 1);

insert into match(description, match_date, match_time, team_a, team_b, sport)
values ('OSFP-MONACO', '2020-07-20', '17:45', 'OSFP', 'MONACO', 2);
