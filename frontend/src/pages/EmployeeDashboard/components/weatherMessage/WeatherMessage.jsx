import React, { useState, useEffect } from 'react';

const WeatherMessage = ({ userLocation }) => {

const URL = `https://api.open-meteo.com/v1/forecast?latitude=${userLocation.latitude}&longitude=${userLocation.longitude}&current=weather_code`

const [weatherCode, setWeatherCode] = useState(null);

const weatherCodeMapping = {
    0: "Sunny days, clear skies!️ ☀️",
    1: "Almost clear, enjoy the sunshine! ☀️",
    2: "Partly cloudy ⛅",
    3: "Overcast ☁️",
    45: "A foggy day, drive safely! 🌫️",
    48: "A foggy day, drive safely! 🌫️",
    51: "A little drizzle, grab an umbrella! 🌧️",
    53: "Some drizzle, stay dry! 🌧️",
    55: "Heavy drizzle, don't forget your raincoat! 🌧️",
    56: "Light drizzle, keep dry! 🌧️",
    57: "Drizzle outside, stay cozy! 🌧️",
    61: "Light rain, perfect for a walk! 🌦️",
    63: "It's raining, get your umbrella! 🌧️",
    65: "Heavy rain, grab an umbrella! 🌧️",
    66: "Freezing rain, watch your step! ❄️🌧️",
    67: "Heavy freezing rain, stay safe! ❄️🌧️",
    71: "Light snow, enjoy the winter wonderland! 🌨️",
    73: "Snowy day, time for snowmen! 🌨️",
    75: "Heavy snow, stay warm! 🌨️",
    77: "Heavy snow, perfect for skiing! 🌨️",
    80: "Slight rain showers, enjoy the fresh air! 🌦️",
    81: "Rain showers, watch out for puddles! 🌧️",
    82: "Heavy rain showers, stay dry! 🌧️",
    85: "Snow showers, time for hot cocoa! 🌧️",
    86: "Heavy snow showers, stay cozy! 🌧️",
    95: "Thunderstorm, stay safe! ⛈️",
    96: "Slight thunderstorm, stay safe! ⛈️",
    99: "Heavy thunderstorm, stay dry! ⛈️"
};


    useEffect(() => {
        const fetchWeatherCode = async () => {
            try {
                const weather = await fetch(URL);
                const weatherData = await weather.json();
                setWeatherCode(weatherData.current.weather_code);
            } catch (error) {
                console.error('Failed to fetch weather', error);
            }
        };

        fetchWeatherCode();
    }, [URL]);



    return (

        <>
<h6>{weatherCodeMapping[weatherCode]}</h6>
        </>

        )

        }
    export default WeatherMessage;