# Discord-Bot
This is a simple discord bot written in Java for community servers (Work In Progress).

### Features
A command to shut down the bot

## Usage
| Config Option | Description                                                               | Required | Default|
|---------------|---------------------------------------------------------------------------| --- |----|
| token         | Discord Bot Token.                                                        | yes |`"BOT_TOKEN"`|
| prefix        | Prefix for the bot.                                                       | no | `!`|
| status        | Displays the status of the bot. [ONLINE, IDLE, DO_NOT_DISTURB, INVISIBLE] | no | `ONLINE`|
| activity      | Displays the activity of the bot.                                         | no | `games`|

## Getting started

### Discord
1. Go to https://discord.com/developers/applications
2. Create a new application
3. Create a bot in your application
4. Retreive the bot's token and set it as the `token` in the config.txt
5. Go to `https://discordapp.com/oauth2/authorize?client_id=<YOUR_BOT_CLIENT_ID>&scope=bot&permissions=36785216`
6. Add the bot to your server

### Other Info
- The program will save your token to ./config.txt. To use another bot, simply replace the token.

### Bot commands

Assuming `prefix` is set to `!`.

#### Shutdown
```
!shutdown
OUTPUT:
Shuts the bot down
```
===================

By Rylan P. and Christopher J.
