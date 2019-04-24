CREATE TABLE tasks_table
(
  id        varchar PRIMARY KEY,
  text      varchar   NOT NULL,
  status    varchar   NOT NULL,
  createdAt timestamp NOT NULL
);