dropdb witcher
createdb witcher 2>/dev/null
psql -h localhost -d witcher -f init.sql -f default_values.sql
