SQL
connect 'jdbc:derby:shipsDB;create=false;';
connect 'jdbc:derby:manfDB;create=false;';

connect 'jdbc:derby://localhost:1527/shipsDB;create=false;';
connect 'jdbc:derby://localhost:1527/manfDB;create=false;';
connect 'jdbc:derby://localhost/studentdb;create=true;';

DATABASE
ssh -y lxyamerica@linux2.cse.tamu.edu
mysql -h database2.cs.tamu.edu -u ckx9411sx -p
mysql -h database2.cs.tamu.edu -u lxyamerica -p

COMMANDS
create database `ckx9411sx-testDB`;
drop database `lxyamerica-shipsDB`;
use `lxyamerica-shipsDB`;
use `ckx9411sx-shipsDB`;