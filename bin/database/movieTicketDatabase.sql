DROP DATABASE IF EXISTS MovieTicket;
CREATE DATABASE  MovieTicket;
USE  MovieTicket;

DROP TABLE IF EXISTS THEATRE;
CREATE TABLE THEATRE (
	TheatreID    integer not null,
    Theatre_name  varchar(30) not null,
    Address  varchar(30),
    primary key(TheatreID)
    );
    
DROP TABLE IF EXISTS MOVIE;
CREATE TABLE MOVIE (
    MovieID 		INTEGER NOT NULL,
    Mov_Name 		VARCHAR(30) NOT NULL,
    TheatreID		integer, 
    PRIMARY KEY (MovieID)
    #FOREIGN KEY (TheatreID) REFERENCES THEATRE(TheatreID)
);



DROP TABLE IF EXISTS REGISTERED_USER;
CREATE TABLE REGISTERED_USER (
	userName   		varchar(45) not null,
    address			varchar(45),
    accountNumber   varchar(45),
    email 			varchar(45),
    primary key (accountNumber)
    );


DROP TABLE IF EXISTS TICKET;
CREATE TABLE TICKET (
theaterName         varchar(45),
userInfo            varchar(45),
bookingReference    varchar(45) not null,
movie               varchar(45),
dateTime             varchar(45),
primary key(bookingReference )
 );
    

    
DROP TABLE IF EXISTS PAYMENT;
CREATE TABLE PAYMENT (
	PaymentID integer not null,
    UserID int not null,
	TicketID    integer not null,
    Price integer not null,
    primary key(PaymentID)
    -- foreign key (UserID) references REGISTERED_USER(UserID),
--     foreign key (TicketID) references TICKET(TicketID)
    );   
    
DROP TABLE IF EXISTS SHOW_TIME;
CREATE TABLE SHOW_TIME (
	ShowTimeID		integer not null auto_increment, 
    MovieID			integer,
    ShowDate		varchar (45),
    ShowTime		time,
    primary key (ShowTimeID)
    
    );
  
DROP TABLE IF EXISTS SEAT;
CREATE TABLE SEAT (
	state		   integer not null, 
    seatNumber	   integer not null auto_increment ,
    seatRow		    integer,
    seatColumn		integer,
    primary key (seatNumber)
   );

    
INSERT INTO  THEATRE(TheatreID, Theatre_name, Address)
VALUES	
(100, 'Cineplex Odeon', '205 E Hills Blvd SE'),
(200, 'Magiple Cinima', '34 ACC AV NW'),
(300, 'Gongle Plaza', '64 Molly Av SW'),
(400, 'Tim Croos', '56 Runmer Way NE');

INSERT INTO  MOVIE(MovieID, Mov_name, TheatreID)
VALUES  (2000, 'Iron Man', 100),
		(2001, 'John Wick: Chapter 3', 100),
		(2002, 'Tenet', 300),
		(2003, 'Avengers Endgame', 100),
        (2004, 'Star Wars', 200);
 

        
 INSERT INTO  SHOW_TIME(MovieID, ShowDate, ShowTime)
VALUES  
		(2000, '2021-12-09', '12:00:00'),
		(2000, '2021-12-09', '16:00:00'),
        (2000, '2021-12-09', '19:30:00'),
        (2000, '2021-12-07', '10:00:00'),
		(2000, '2021-12-07', '16:45:00'),
        (2000, '2021-12-07', '18:30:00'),
		(2001, '2021-12-07', '12:45:00'),
		(2001, '2021-12-07', '16:40:00'),
        (2001, '2021-12-10', '19:15:00'),
        (2001, '2021-12-10', '12:45:00'),
		(2001, '2021-12-10', '14:40:00'),
        (2001, '2021-12-10', '17:15:00'),
		(2002, '2021-12-10', '13:30:00'),
		(2002, '2021-12-10', '15:45:00'),
        (2002, '2021-12-11', '21:00:00'),
        (2002, '2021-12-11', '14:35:00'),
		(2002, '2021-12-12', '16:55:00'),
        (2002, '2021-12-12', '20:15:00'),
		(2003, '2021-12-12', '15:25:00'),
		(2003, '2021-12-12', '18:00:00'),
        (2003, '2021-12-12', '20:00:00'),
        (2003, '2021-12-15', '12:25:00'),
		(2003, '2021-12-15', '17:25:00'),
        (2003, '2021-12-11', '19:15:00'),
        (2004, '2021-12-12', '16:00:00'),
        (2004, '2021-12-123', '17:25:00'),
		(2004, '2021-12-24', '18:50:00'),
        (2004, '2021-12-25', '15:20:00'),
        (2004, '2021-12-25', '17:25:00'),
		(2004, '2021-12-28', '18:30:00');

        
INSERT INTO  REGISTERED_USER(userName, address, accountNumber, email)
VALUES  ('Ally Bur', '32 NW Calgary', '734694398475946', 'ally.bur@ucalgary.ca' ),
		('John Doe', '67 NW Calgary', '893749304504546', 'john.doe@ucalgary.ca'),
        ('Bella May', '56 SE Calgary', '399238340644334', 'bell.amy@ucalgary.ca'),
        ('Patty Pat', '76 SW Calgary', '128994830843044','patty.paty@ucalgary.ca' );
        
INSERT INTO  TICKET(theaterName, userInfo, bookingReference, movie, dateTime)
VALUES  
('Cineplex Odeon','John Mon', '2001','Tenet', '2021-12- 27');
		        
INSERT INTO  MovieTicket.PAYMENT(PaymentID, UserID, TicketID, Price)
VALUES  (001, 1, 2, 15.00),
		(002, 2, 2, 15.00),
        (003, 3, 3, 15.00);
        
INSERT INTO SEAT (state, seatRow, seatColumn)
VALUES (0, 1, 1), 
        (0, 1, 2), 
        (0, 1, 3);

SELECT * FROM REGISTERED_USER; 

