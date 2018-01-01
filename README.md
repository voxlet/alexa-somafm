# Soma FM Alexa Skill
## Rationale

A quick and dirty implementation of a Soma FM skill for my personal use, since the official one is currently not available in Japan. Only plays Fluid, which is what I code to 95% of the time.

The following sections are from the [Alexa Skills Template](https://github.com/jreighley/alexatemplate).

## Prerequisites

You will need to set up [Amazon Web Services CLI] (http://docs.aws.amazon.com/cli/latest/userguide/installing.html) on your machine.   All Alexa Skills are hosted out of the US-EAST-1, so you will want to set your configuration accordingly.

You will also need [Leiningen] (https://leiningen.org/)

## Deploying

Run `lein cljs-lambda default-iam-role` if you don't have yet have suitable
execution role to place in your project file.  This command will create an IAM
role under your default (or specified) AWS CLI profile, and modify your project
file to specify it as the execution default.

Otherwise, add an IAM role ARN under the function's `:role` key in the
`:functions` vector of your profile file, or in `:cljs-lambda` -> `:defaults` ->
`:role`.

Then:

```sh
$ lein cljs-lambda deploy
```

## Configuring your Alexa skill

Go to the [AWS lambda Console] (https://console.aws.amazon.com/lambda/home?region=us-east-1)
Select your function  (alexa-magic)
Click on the triggers tab
Click on the input box, and select Alexa Skills kit.

Go to the in the amazon developers tools go to the [Alexa Skills list] (https://developer.amazon.com/edw/home.html#/skills/list) and add your new skill.

On the Interaction tab:

Configure your intents like this:
```
{
  "intents": [
    {
      "intent": "GetHello"
    },
    {
      "intent": "GetGoodbye"
    }
  ]
}
```

Configure your utterences like this:

```
GetHello  Hi
GetHello  Hello
GetGoodbye goodbye
GetGoodbye Bye
```

On the Configuration tab,
Select AWS Lambda ARN (Amazon Resource Name)
Enter you ARN --  You can find this in the upper right of your the AWS lambda Console

On the test tab:
Test with Hello, Goodbye,  Open.

## Program Away!
Modify your core.cljs functions to satisfy your goals.

Save your files then

```
lein cljs-lambda deploy
```
Will re-compile and deploy your changes.   Test and repeat until happy!


