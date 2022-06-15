### Task

1 - Actuator metrics/process.start.time endpoint üzerinden uygulamanın başlangıç zamanı alınacak (http://localhost:8080/actuator/metrics/process.start.time)
2 - Mapping işlemi yapılacak (Object mapper kullanılablir)
3 - Json bind etmek için gerekli obje oluşturulacak
4 - Json içerisinde yer alan unix epoch formatı (measurements.value) LocalDateTime tipine convert edilecek
5 - Endpoint üzerinden aşağıdaki örneğe benzer bir string dönülecek
    * Server is running since : (converted measurement.value)



