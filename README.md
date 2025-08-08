# MangoLimiter 🥭

A Minecraft plugin to limit players' flying height based on permissions. Ideal for survival or factions servers.

## ✨ Features

- Configurable max fly height
- Multilingual support (`en`, `es`)
- Full permission control (`mangolimiter.use`, `mangolimiter.bypass`)
- Lightweight and optimized

## 📦 Installation

1. Download the latest `MangoLimiter.jar`
2. Drop it into your `/plugins` folder
3. Restart your server
4. Edit `config.yml` and `lang/*.yml` as needed

## 📄 Permissions

| Permission             | Description                            | Default |
|------------------------|----------------------------------------|---------|
| `mangolimiter.use`     | Allows access to commands              | false   |
| `mangolimiter.bypass`  | Allows flying above max height limit   | false   |

## 📚 Commands

| Command               | Description              |
|-----------------------|--------------------------|
| `/mangolimiter help`  | Show help message        |
| `/mangolimiter reload`| Reload config/lang files |

## 🛠️ Configuration

See `config.yml` and `lang/en.yml` or `lang/es.yml` for full customization.

## 🐞 Debug Mode

Enable `debug: true` in `config.yml` to log permission checks in the console for testing.

---

**Made with ❤️ by MangoFuhrer**
