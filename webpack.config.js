var path = require('path');

module.exports = {
    entry: './src/main/js/app.jsx',
    devtool: 'sourcemaps',
    //cache: true,
    debug: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/js/bundle.js'
    },
    module: {
        loaders: [
            {
            	test: /\.jsx?$/,
                exclude: /(node_modules)/,
                loader: 'babel',
                query: {
                    //cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            }
        ]
    }
};