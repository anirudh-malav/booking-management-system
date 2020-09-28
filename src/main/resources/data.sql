INSERT INTO USER_DETAILS (ID, EMAIL_ID, PASSWORD, USER_ID) VALUES('1','abc','123','777') ;
INSERT INTO USER_DETAILS (ID, EMAIL_ID, PASSWORD, USER_ID) VALUES('2','amalav','123456','888') ;

INSERT INTO City (CITY_ID, CITY_NAME, VERSION) VALUES('1','Delhi', '1') ;
INSERT INTO City (CITY_ID, CITY_NAME, VERSION) VALUES('2','Goa', '1') ;
INSERT INTO City (CITY_ID, CITY_NAME, VERSION) VALUES('3','Kota', '1') ;
INSERT INTO City (CITY_ID, CITY_NAME, VERSION) VALUES('4','Banglore', '1') ;

INSERT INTO Theatre (THEATRE_ID, THEATRE_NAME, CITY_CITY_ID, VERSION) VALUES('11','PVR','1','1') ;
INSERT INTO Theatre (THEATRE_ID, THEATRE_NAME, CITY_CITY_ID, VERSION) VALUES('12','INOX','1','1') ;
INSERT INTO Theatre (THEATRE_ID, THEATRE_NAME, CITY_CITY_ID, VERSION) VALUES('13','CENTRAL','2','1') ;

INSERT INTO Show (SHOW_ID, SHOW_NAME, VERSION) VALUES('21','movie1','1') ;
INSERT INTO Show (SHOW_ID, SHOW_NAME, VERSION) VALUES('22','movie2','1') ;
INSERT INTO Show (SHOW_ID, SHOW_NAME, VERSION) VALUES('23','movie3','1') ;
INSERT INTO Show (SHOW_ID, SHOW_NAME, VERSION) VALUES('24','movie4','1') ;

INSERT INTO Seat (SEAT_ID, PRICE, VERSION) VALUES('51','400','1') ;
INSERT INTO Seat (SEAT_ID, PRICE, VERSION) VALUES('52','400','1') ;
INSERT INTO Seat (SEAT_ID, PRICE, VERSION) VALUES('53','400','1') ;

INSERT INTO THEATRE_SHOW_LIST (THEATRE_THEATRE_ID,SHOW_LIST_SHOW_ID) VALUES('11','21') ;
INSERT INTO THEATRE_SHOW_LIST (THEATRE_THEATRE_ID, SHOW_LIST_SHOW_ID) VALUES('11','22') ;
INSERT INTO THEATRE_SHOW_LIST (THEATRE_THEATRE_ID, SHOW_LIST_SHOW_ID) VALUES('11','23') ;

INSERT INTO SHOW_SEAT (ID,SHOW_ID, SEAT_ID, STATUS, MODIFY_DATE, VERSION) VALUES('1', '21','51','AVAILABLE','1971-01-01 00.00.00.000','1') ;
INSERT INTO SHOW_SEAT (ID,SHOW_ID, SEAT_ID, STATUS, MODIFY_DATE, VERSION) VALUES('2','21','52','AVAILABLE','1971-01-01 00.00.00.000','1') ;
INSERT INTO SHOW_SEAT (ID,SHOW_ID, SEAT_ID, STATUS, MODIFY_DATE, VERSION) VALUES('3','21','53','AVAILABLE','1971-01-01 00.00.00.000','1') ;
INSERT INTO SHOW_SEAT (ID,SHOW_ID, SEAT_ID, STATUS, MODIFY_DATE, VERSION) VALUES('4','22','51','AVAILABLE','1971-01-01 00.00.00.000','1') ;
INSERT INTO SHOW_SEAT (ID,SHOW_ID, SEAT_ID, STATUS, MODIFY_DATE, VERSION) VALUES('5','22','52','AVAILABLE','1971-01-01 00.00.00.000','1') ;
INSERT INTO SHOW_SEAT (ID,SHOW_ID, SEAT_ID, STATUS, MODIFY_DATE, VERSION) VALUES('6','22','53','AVAILABLE','1971-01-01 00.00.00.000','1') ;
INSERT INTO SHOW_SEAT (ID,SHOW_ID, SEAT_ID, STATUS, MODIFY_DATE, VERSION) VALUES('7','23','51','AVAILABLE','1971-01-01 00.00.00.000','1') ;
INSERT INTO SHOW_SEAT (ID,SHOW_ID, SEAT_ID, STATUS, MODIFY_DATE, VERSION) VALUES('8','23','52','AVAILABLE','1971-01-01 00.00.00.000','1') ;
INSERT INTO SHOW_SEAT (ID,SHOW_ID, SEAT_ID, STATUS, MODIFY_DATE, VERSION) VALUES('9','23','53','AVAILABLE','1971-01-01 00.00.00.000','1') ;
