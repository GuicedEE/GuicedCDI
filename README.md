# 🫘 GuicedEE CDI Bridge

[![JDK](https://img.shields.io/badge/JDK-25%2B-0A7?logo=java)](https://openjdk.org/projects/jdk/25/)
[![Build](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

<!-- Tech icons row -->
![Guice](https://img.shields.io/badge/Guice-Core-2F4F4F)
![CDI](https://img.shields.io/badge/Jakarta-CDI-2962FF)
![JPMS](https://img.shields.io/badge/JPMS-Modules-0A7)

GuicedEE CDI is a lightweight bridge that integrates Google Guice modules with Jakarta CDI runtimes. Use it when you need Guice’s module ergonomics alongside CDI-managed beans and scopes.

## ✨ Features
- Bridge Guice bindings into CDI-managed applications
- Discover Guice modules via ServiceLoader and/or JPMS providers
- Co-existence of scopes and interceptors where supported by the host environment
- Small surface area; keep your DI clean and modular

## 📦 Install (Maven)
```
<dependency>
  <groupId>com.guicedee</groupId>
  <artifactId>guiced-cdi</artifactId>
</dependency>
```

## 🚀 Quick Start
```
// 1) Provide your Guice module
public final class MyModule extends com.google.inject.AbstractModule {
  @Override protected void configure() {
    bind(Greeter.class).to(DefaultGreeter.class);
  }
}

// 2) Register via ServiceLoader (META-INF/services)
// file: META-INF/services/com.guicedee.client.IGuiceModule
//   com.example.MyModule

// 3) Start your CDI container
// The bridge exposes Guice bindings to CDI consumers when your app boots.
```

## ⚙️ Configuration
- Register Guice modules via `META-INF/services` and/or JPMS `provides ... with ...`.
- Logging and environment toggles live in your host app; the bridge itself is configuration-light.
- In hybrid stacks (Servlet/JAX-RS/Vert.x), ensure only one container owns lifecycle; others integrate via SPI.

## 🧩 JPMS & SPI
- JPMS-friendly; dual-register services for portability (ServiceLoader + module-info.java).
- If you export Guice modules as services, add `provides com.guicedee.client.IGuiceModule with ...`.

## 🧪 Example (CDI bean consumes a Guice-provided binding)
```
@jakarta.enterprise.context.ApplicationScoped
public class WelcomeResource {
  @jakarta.inject.Inject Greeter greeter; // bound from MyModule

  public String hello(String name) { return greeter.greet(name); }
}
```

## 📚 Docs & Rules
- Pact: `PACT.md`
- Rules: `RULES.md`
- Guides: `GUIDES.md`
- Glossary: `GLOSSARY.md`
- Architecture index: `docs/architecture/README.md`

## 🤝 Contributing
- Issues/PRs welcome. Keep docs in sync with changes (forward-only policy).
- Align terminology with the topic glossaries referenced from `GLOSSARY.md`.

## 📝 License
- Apache License 2.0 — see `LICENSE`.
