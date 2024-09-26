CREATE TABLE public.patient_address
(
    id                      serial4       NOT NULL,
    street                  varchar(255)  NULL,
    "number"                varchar(14)   NULL,
    city                    varchar(255)  NULL,
    complement              varchar(255)  NULL,
    neighborhood            varchar(255)  NULL,
    state                   varchar(255)  NULL,
    country                 varchar(255)  NULL,
    zip_code                varchar(10)   NULL,
    patient_id              int4          NULL,
    updated_at              timestamptz   NULL,
    deleted_tmsp            timestamptz   NULL,
    created_at              timestamptz   NULL,
    created_by_id           int8          NULL,
    updated_by_id           int8          NULL,
    CONSTRAINT patient_address_pkey PRIMARY KEY (id),
    CONSTRAINT fk_patient_address_patient_id FOREIGN KEY (patient_id) REFERENCES public.patient (id)
);