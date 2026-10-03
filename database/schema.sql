-- Database initialization script
-- (Do not include real credentials or user tables here yet)

CREATE TABLE IF NOT EXISTS system_config (
    config_key VARCHAR(50) PRIMARY KEY,
    config_value VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT IGNORE INTO system_config (config_key, config_value) 
VALUES ('app_name', 'practice_web_application');
