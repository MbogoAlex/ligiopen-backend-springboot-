ALTER TABLE match_fixture DROP CONSTRAINT match_fixture_status_check;

ALTER TABLE match_fixture ADD CONSTRAINT match_fixture_status_check
    CHECK (status IN ('PENDING', 'PLAYING', 'COMPLETED', 'CANCELLED'));
