# docker Postgresql

```bash

docker run --rm -itd -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 --name postgresql postgres

```

# postgres slow query

```sql

create or replace function slow_query()
returns table (numero int)
language plpgsql
as
$$

begin
	for loop_counter in 1 .. 200000000 loop
      --raise notice 'counter: %', loop_counter;
   end loop;
   
   RETURN QUERY
   SELECT floor(random()*1000)::int;
end;
$$;


select * from slow_query ()

select numero from slow_query ()

```

# slow_view

# Urls
- Dashboard
  http://localhost:8080/dashboard

- Task long
  http://webservice1.docker.localhost/vista




