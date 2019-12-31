dropdb -p 5545 witcher
createdb -p 5545 witcher 2>/dev/null
psql -p 5545 -h localhost -d witcher -f init.sql -f default_values.sql
