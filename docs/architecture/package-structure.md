# Package Structure

The base package is:

```text
io.github.trieunguyenphu.digitalbanking
```

It currently contains only the Spring Boot application class. Do not create empty packages for anticipated features.

As real capabilities are added, prefer package-by-feature organization. A future shape might be:

```text
digitalbanking/
|-- customer/
|-- account/
|-- transfer/
`-- shared/
```

These names are illustrative, not a commitment to implement those features. Within a feature, introduce technical subpackages such as `api`, `application`, `domain`, or `persistence` only when the feature's actual size and dependencies make them useful. Keep shared code small and move something there only after it is genuinely shared.
