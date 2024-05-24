CREATE TABLE IF NOT EXISTS public.user_profile (
    uid int8 NOT NULL,
    user_name varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    aud_created_by varchar(50) NOT NULL,
    aud_created_date timestamp NOT NULL,
    aud_last_modified_by varchar(50) NOT NULL,
    aud_last_modified_date timestamp NOT NULL,
    CONSTRAINT user_profile_pk PRIMARY KEY (uid),
    CONSTRAINT user_profile_unique UNIQUE(user_name)
);

CREATE SEQUENCE if not exists public.seq_user_profile
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE INDEX idx_user_profile_user_name ON user_profile(user_name);

--

CREATE TABLE IF NOT EXISTS public.user_experience (
    uid int8 NOT NULL,
    user_id int8 NOT NULL,
    company_name varchar(255) NOT NULL,
    start_time timestamp NOT NULL,
    end_time timestamp default NULL,
    domain varchar(100) NOT NULL,
    sub_domain varchar(100) NOT NULL,
    aud_created_by varchar(50) NOT NULL,
    aud_created_date timestamp NOT NULL,
    aud_last_modified_by varchar(50) NOT NULL,
    aud_last_modified_date timestamp NOT NULL,
    CONSTRAINT user_experience_pk PRIMARY KEY (uid),
	CONSTRAINT user_experience_fk FOREIGN KEY(user_id) REFERENCES user_profile(uid)
);

CREATE SEQUENCE if not exists public.seq_user_experience
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE INDEX idx_user_experience_user_id ON user_experience(user_id);

--

CREATE TABLE IF NOT EXISTS public.user_skills (
    uid int8 NOT NULL,
    user_id int8 NOT NULL,
    user_experience_id int8 NOT NULL,
    skill varchar(255) NOT NULL,
    aud_created_by varchar(50) NOT NULL,
    aud_created_date timestamp NOT NULL,
    aud_last_modified_by varchar(50) NOT NULL,
    aud_last_modified_date timestamp NOT NULL,
    CONSTRAINT user_skills_pk PRIMARY KEY (uid),
	CONSTRAINT user_skills_fk1 FOREIGN KEY(user_id) REFERENCES user_profile(uid),
	CONSTRAINT user_skills_fk2 FOREIGN KEY(user_experience_id) REFERENCES user_experience(uid)
);

CREATE SEQUENCE if not exists public.seq_user_skills
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE INDEX idx_user_skills_user_experience_id ON user_skills(user_experience_id);
CREATE INDEX idx_user_skills_user_id ON user_skills(user_id);

--

CREATE TABLE IF NOT EXISTS public.skill_relationship (
    skill_1 varchar(255) NOT NULL,
    skill_2 varchar(255) NOT NULL,
    relation varchar(255) NOT NULL,
    aud_created_by varchar(50) NOT NULL,
    aud_created_date timestamp NOT NULL,
    aud_last_modified_by varchar(50) NOT NULL,
    aud_last_modified_date timestamp NOT NULL
);

--

CREATE TABLE IF NOT EXISTS public.endorsements (
    uid int8 NOT NULL,
    endorser int8 NOT NULL,
    endorsee int8 NOT NULL,
    skill varchar(255) NOT NULL,
    actual_score float NOT NULL,
    system_calculated_weight float NOT NULL,
    reason varchar(1000) NOT NULL,
    aud_created_by varchar(50) NOT NULL,
    aud_created_date timestamp NOT NULL,
    aud_last_modified_by varchar(50) NOT NULL,
    aud_last_modified_date timestamp NOT NULL,
    CONSTRAINT endorsements_pk PRIMARY KEY (uid)
);

CREATE SEQUENCE if not exists public.seq_endorsements
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE INDEX idx_endorsements_endorsee ON endorsements(endorsee);