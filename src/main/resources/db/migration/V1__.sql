create TABLE potato (
  id UUID NOT NULL,
  name VARCHAR(255),
  processed_form VARCHAR(255),
  CONSTRAINT pk_potato PRIMARY KEY (id)
);

create TABLE tomato (
  id UUID NOT NULL,
  name VARCHAR(255),
  processed_form VARCHAR(255),
  CONSTRAINT pk_tomato PRIMARY KEY (id)
);