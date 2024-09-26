CREATE TABLE public.health_professional
(
    id                          bigserial     NOT NULL,
    "name"                      varchar(255)  NOT NULL,
    cpf                         varchar(14)   NOT NULL,
    council_register            varchar(20)   NOT NULL,
    health_professional_type    int4          NOT NULL,
    telephone                   varchar(20)   NULL,
    email                       varchar(255)  NULL,
    specialty                   varchar(255)  NULL,
    updated_at                  timestamptz   NULL,
    deleted_tmsp                timestamptz   NULL,
    created_at                  timestamptz   NULL,
    created_by_id               int8          NULL,
    updated_by_id               int8          NULL,
    CONSTRAINT health_professional_pkey PRIMARY KEY (id)
);