# COVID-19 Data Analysis

## Overview

This project involves analyzing COVID-19 data to extract meaningful insights using Python libraries such as pandas and Plotly. The notebook includes steps for data preprocessing, exploratory data analysis, and generating visualizations to understand the spread and impact of the pandemic.

## Features

- Importing and cleaning the dataset (`country_wise_latest.csv`)
- Exploratory Data Analysis (EDA) to uncover patterns and trends
- Visualizations created using Plotly, including:
  - Bar charts showing countries with the highest confirmed cases
  - Pie charts displaying the distribution of cases by WHO regions
  - Scatter plots for trends in new cases, deaths, and recoveries
  - Choropleth maps for 1-week percentage increases and mortality rates
  - Box plots for death rates by WHO region
  - Treemaps of confirmed cases by WHO region and country
- Insights into COVID-19 metrics such as recovery rates, mortality rates, and regional impacts

## Dataset

The dataset `country_wise_latest.csv` contains COVID-19 statistics for various countries, including:

- Confirmed cases
- Deaths
- Recovered
- Active cases
- New cases, deaths, and recoveries
- Deaths per 100 cases and recoveries
- Weekly changes in confirmed cases
- WHO region classification

The dataset is sourced from [Kaggle](https://www.kaggle.com/datasets/imdevskp/corona-virus-report). Ensure the dataset is placed in the same directory as the notebook or update the file path in the code.

## Prerequisites

- Jupyter Notebook
- Required Python libraries:
  - pandas
  - plotly

## Results

- Bar chart showing the top 10 countries with the highest confirmed cases.
- Pie chart illustrating the distribution of confirmed cases by WHO region.
- Scatter plots for trends in new cases, deaths, and recoveries.
- Choropleth maps displaying weekly percentage increases and mortality rates.
- Box plot for death rates by WHO region.
- Treemap of confirmed cases by WHO region and country.
