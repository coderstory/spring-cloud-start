DELETE FROM user;

INSERT INTO user (id, name,username,password,enable, age, email) VALUES
  (1, 'Jone','Jone', '$2a$10$7VPnVxObwYHtfuXdH9HZ/.O1i.wqAAYN77Sxi7CdOKao7uRTmi2k6',true,18, 'test1@baomidou.com'),
  (2, 'Jack','Jack', '$2a$10$7VPnVxObwYHtfuXdH9HZ/.O1i.wqAAYN77Sxi7CdOKao7uRTmi2k6',true,20, 'test2@baomidou.com'),
  (3, 'Tom','Tom', '$2a$10$7VPnVxObwYHtfuXdH9HZ/.O1i.wqAAYN77Sxi7CdOKao7uRTmi2k6',true,28, 'test3@baomidou.com'),
  (4, 'Sandy','Sandy', '$2a$10$7VPnVxObwYHtfuXdH9HZ/.O1i.wqAAYN77Sxi7CdOKao7uRTmi2k6',true,21, 'test4@baomidou.com'),
  (5, 'Billie','Billie', '$2a$10$7VPnVxObwYHtfuXdH9HZ/.O1i.wqAAYN77Sxi7CdOKao7uRTmi2k6',true,24, 'test5@baomidou.com');

  INSERT INTO Authority (id, userId,Authority) VALUES
  (1, 1,'ROLE_ADMIN'),
  (2, 2,'ROLE_ADMIN'),
  (3, 3,'ROLE_USER'),
  (4, 4,'ROLE_USER'),
  (5, 5,'ROLE_USER');