# va corporate srv
This backend is for handling reporting and other service exclude PMK183.

# Dynamic function query
pattern findAllQueryDynamic Repository: findAllQueryDynamic

cara penggunaan
1.Spesifikasikan valid column yg mau di searching di variable validColumns pada service class
2.Spesifikasikan column2 yg type variable nya int di variable intColumn pada service class

cara penggunaan endpoint
1.Tinggal ditambahkan saja query params pada url, misal ingin searching id, contoh : /api/v2.4/korporat/find-all?page=1&size=10&id=1 atau ingin searching corporate_name, contoh : /api/v2.4/korporat/find-all?page=1&size=10&corporate_name=Askrida




