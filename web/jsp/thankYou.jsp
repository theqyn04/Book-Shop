<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thank You</title>
        <style>
            /* Reset CSS */
            body, h1, p {
                margin: 0;
                padding: 0;
                font-family: Arial, sans-serif;
            }

            /* Container */
            .container {
                max-width: 800px;
                margin: 50px auto;
                padding: 20px;
                text-align: center;
                background-color: #f9f9f9;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            /* Header */
            h1 {
                font-size: 2.5em;
                color: #4CAF50;
                margin-bottom: 20px;
            }

            /* Button */
            .btn {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                font-size: 1em;
                color: #fff;
                background-color: #4CAF50;
                border: none;
                border-radius: 5px;
                text-decoration: none;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn:hover {
                background-color: #45a049;
            }

            /* Footer */
            footer {
                margin-top: 50px;
                text-align: center;
                font-size: 0.9em;
                color: #777;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <!-- Main Content -->
        <div class="container">
            <h1>Thank you for your purchase!</h1>
            <p>Your order has been successfully placed. We appreciate your business!</p>
            <a href="HomeURL" class="btn">Return to Home</a>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>