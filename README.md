# CoderAssistant

An IntelliJ plugin to query StackOverflow automatically. (Course Project for Advanced Software Engineering)



### Highlight Features

##### Search StackOverflow Without Leaving the IDE

CoderAssistant integrates seamlessly with Intellij to allow you to find the solutions to the development problems you face without ever leaving the your development environment.



##### Automatically Generate Queries from your Code

Take your automation one step further by having CoderAssistant generate queries for you. Either generate a query from an editor tab or select a block of code, then right-click and select Auto Query! This function is implemented though training a model using CodeBERT.



##### Automatically Generate Queries from the error message

CoderAssistant frees you from 'copy-and-paste' by generating query directly from the error message. And what makes a difference is that this is totally automatic.



********

## How to Install

You can install this plugin through donwloading the release package, which is a [zipfile](https://github.com/JasonZhu-WHU/CoderAssistant/releases/download/v1.0.0/CoderAssistant-1.0.0.jar).

And then import this plugin zip package into the Idea-2017-2, which could be downloaded from here: [windows](https://download.jetbrains.com/idea/ideaIC-2017.2.7.win.zip?_gl=1*10nj294*_ga*MTY4NjkxMDc3Mi4xNjI5MzQwOTgz*_ga_V0XZL7QHEB*MTYzODc3MTQzMy44LjAuMTYzODc3MTQ1MS4w&_ga=2.9619957.1923004857.1638771434-1686910772.1629340983), [mac](https://download.jetbrains.com/idea/ideaIC-2017.2.7.dmg?_gl=1*1x7oi22*_ga*MTY4NjkxMDc3Mi4xNjI5MzQwOTgz*_ga_V0XZL7QHEB*MTYzODc3MTQzMy44LjAuMTYzODc3MTQ1MS4w&_ga=2.240371779.1923004857.1638771434-1686910772.1629340983) and the [official-website](https://www.jetbrains.com/idea/download/other.html).

To make use of the code context search function, you can download the trained model from [here](https://github.com/JasonZhu-WHU/CoderAssistantModel). 

## Procedures to Build the Project

Firstly, Download Idea 2017-2 for [windows](https://download.jetbrains.com/idea/ideaIC-2017.2.7.win.zip?_gl=1*10nj294*_ga*MTY4NjkxMDc3Mi4xNjI5MzQwOTgz*_ga_V0XZL7QHEB*MTYzODc3MTQzMy44LjAuMTYzODc3MTQ1MS4w&_ga=2.9619957.1923004857.1638771434-1686910772.1629340983), and for [mac](https://download.jetbrains.com/idea/ideaIC-2017.2.7.dmg?_gl=1*1x7oi22*_ga*MTY4NjkxMDc3Mi4xNjI5MzQwOTgz*_ga_V0XZL7QHEB*MTYzODc3MTQzMy44LjAuMTYzODc3MTQ1MS4w&_ga=2.240371779.1923004857.1638771434-1686910772.1629340983). [Here](https://www.jetbrains.com/idea/download/other.html)
 is the official website. 
In Idea:
1. View - Tool Windows - Gradle - Refresh Button
2. After generating output directory, expand Tasks Directory in Gradle. And then expand intellij directory.
3. Click 'runIdea' or 'runIde'

## License

[MIT](./LICENSE)

## Acknowledgement

Our work is based on [StackInTheFlow: behavior-driven recommendation system for stack overflow posts](https://damevski.github.io/files/greco_icse18_preprint.pdf) and [CodeBERT: A Pre-Trained Model for Programming and Natural Languages](https://arxiv.org/pdf/2002.08155.pdf)

> Feng Z, Guo D, Tang D, et al. Codebert: A pre-trained model for programming and natural languages[J]. arXiv preprint arXiv:2002.08155, 2020.
>
> Greco C, Haden T, Damevski K. StackInTheFlow: behavior-driven recommendation system for stack overflow posts[C]//Proceedings of the 40th International Conference on Software Engineering: Companion Proceeedings. 2018: 5-8.
