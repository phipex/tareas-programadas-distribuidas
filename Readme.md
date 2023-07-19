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
   SELECT 3;
end;
$$;


select * from slow_query ()

select numero from slow_query ()

```