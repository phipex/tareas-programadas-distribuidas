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


CREATE OR REPLACE VIEW public.slow_view
AS SELECT slow_query.numero
   FROM slow_query() slow_query(numero);