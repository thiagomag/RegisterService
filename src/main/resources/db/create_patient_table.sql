CREATE TABLE public.patient
(
    id                      bigserial     NOT NULL,
    "name"                  varchar(255)  NOT NULL,
    cpf                     varchar(14)   NOT NULL,
    birth_date              date          NOT NULL,
    gender                  varchar(20)   NULL,
    telephone               varchar(20)   NULL,
    email                   varchar(255)  NULL,
    blood_type              varchar(3)    NULL,
    allergies               text          NULL,
    pre_existing_conditions text          NULL,
    updated_at              timestamptz   NULL,
    deleted_tmsp            timestamptz   NULL,
    created_at              timestamptz   NULL,
    created_by_id           int8          NULL,
    updated_by_id           int8          NULL,
    CONSTRAINT patient_pkey PRIMARY KEY (id)
);