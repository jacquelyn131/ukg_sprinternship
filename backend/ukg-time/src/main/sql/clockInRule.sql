delimiter //

CREATE PROCEDURE insert_clock_punch (IN e_id INT, IN newPunchType VARCHAR(9),
OUT recentPunchType VARCHAR(9), OUT canPunch BOOLEAN)
BEGIN
    -- first check if employee has no previous punches
    IF ((SELECT COUNT(*) FROM clock_punch WHERE employee_id = e_id )<= 0)
    THEN IF (newPunchType = 'IN')
        THEN SELECT TRUE INTO canPunch;
        ELSE
            SELECT FALSE INTO canPunch;
        END IF;
    ELSE
        SELECT type INTO recentPunchType FROM db_example.clock_punch
        WHERE employee_id = e_id
        ORDER BY date_time DESC
        LIMIT 1;

        IF (newPunchType = 'IN')
            THEN IF (recentPunchType = 'OUT')
                THEN SELECT TRUE INTO canPunch;
            ELSE
                SELECT FALSE INTO canPunch;-- canPunch
            END IF;
        ELSEIF (newPunchType = 'OUT')
            THEN IF (recentPunchType = 'IN' OR recentPunchType = 'BREAK-IN' OR recentPunchType = 'BREAK-OUT')
                THEN SELECT TRUE INTO canPunch;
            ELSE
                SELECT FALSE INTO canPunch;
            END IF;
        ELSEIF (newPunchType = 'BREAK-IN')
            THEN IF (recentPunchType = 'IN' OR recentPunchType = 'BREAK-OUT')
                THEN SELECT TRUE INTO canPunch;
            ELSE
                SELECT FALSE INTO canPunch;
            END IF;
        ELSEIF (newPunchType = 'BREAK-OUT')
            THEN IF (recentPunchType = 'BREAK-IN')
                THEN SELECT TRUE INTO canPunch;
            ELSE
                SELECT FALSE INTO canPunch;
            END IF;
        ELSE -- not valid type
           SELECT FALSE INTO canPunch;
        END IF;
    END IF;

END //

delimiter ;