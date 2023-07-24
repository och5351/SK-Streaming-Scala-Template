# SK(Spark-Kafka) Structured Streaming Code Template (scala)

<br>

Spark Structured Streaming 을 DI / IoC 디자인 패턴을 적용한 Template 입니다.

지속적으로 업데이트 하여 Kafka 를 읽어오는 Structured Streaming 을 대응 하고자 Repository 를 구성했습니다.

<br><br>

# 정보

<br>

### Version

- Spark 3.2.3
- Scala 2.12.15

### Dependency

```scala
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.1.3",
  "org.apache.spark" %% "spark-sql" % "3.1.3",
  "org.apache.hadoop" % "hadoop-common" % "3.2.1",
  "org.apache.spark" %% "spark-streaming" % "3.1.3",
)
```

<br><br>

# 구조

<br>

<div align="center">
<img src="https://github.com/och5351/SK-Streaming-Scala-Template/assets/45858414/197ad682-7b73-4156-a72e-b4cc650fa955"/>
</div>

<br><br>

# Blog

<br>

<a href="https://velog.io/@och5351/%EB%8D%B0%EC%9D%B4%ED%84%B0-%ED%94%8C%EB%9E%AB%ED%8F%BC-%EC%9A%B4%EC%98%81-%EA%B0%9C%EB%B0%9C-Spark-3-SKSpark-Kafka-Streaming-Code-Template"> Template 소개 및 설명 링크 </a>

<br><br>
