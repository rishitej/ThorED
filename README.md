# ThorED
Lightning Fast Edu Solutions

# What it does
This app has 4 components:
1) Text-to-Code : This will help database students working on SQL to produce custom queries using NLP. It only requires the query as normal text or as a normal human would interpret, and returns a SQL query as output.

2)Story Generator - Input a few lines from your creative brain, and get a whole story in return. This uses AI to construct a story just out of few lines.

3) Summary Framer - Tired of reading a whole document? Wishing you could finish it faster? Using our Summary framer, get a summary of your whole text in minimal lines and get the help you need.

4)Visualization Tool - Want to create beautiful and accurate plots? Using our tool, input you data, and get accurate results in bar plot, scatter plot etc.

# How I built it
The application was built using Android framework wherein, the ML model was build using a NLP based text generation GTP model and fine tuned it according to our tasks. Then ,we created an API using Flask framework in Python. For android application, we used Volley library for HTTP GET and POST methods to communicate with Flask API and used Firebase for authentication and storage.

# Challenges I ran into
API integration Time constraints Difficulty in creating an API using Flask Difficulty executing Volley API calls

# Accomplishments that I'm proud of
We made an application that is useful to every student who is overburdened to learn more than one can grasp. Our application is a small stepping stone to reach a selfless and stress-free future.

# What We learned
Learned using Volley Learned to create an API in Python Learned to integrate ML model with API
