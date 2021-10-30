package model;

import java.util.UUID;

/**
 * an examtoken is a token which is only valid during a specific exam. it enables the EFIT server to see
 * which logged in user, is taking which exam. After login, all communication between EFIT client and EFIT server uses this token.
 */
public interface ExamToken {

    UUID getToken();
}
