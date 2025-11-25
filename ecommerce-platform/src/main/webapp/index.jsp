<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-commerce Platform - Home</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            padding: 20px;
        }
        
        .container {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            border-radius: 20px;
            padding: 40px;
            max-width: 800px;
            width: 100%;
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
            border: 1px solid rgba(255, 255, 255, 0.18);
        }
        
        h1 {
            font-size: 48px;
            margin-bottom: 10px;
            text-align: center;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        }
        
        .subtitle {
            text-align: center;
            font-size: 20px;
            margin-bottom: 30px;
            opacity: 0.9;
        }
        
        .status-box {
            background: rgba(255, 255, 255, 0.15);
            border-radius: 15px;
            padding: 25px;
            margin: 20px 0;
        }
        
        .status-box h3 {
            color: #ffd700;
            margin-bottom: 15px;
            font-size: 24px;
            text-align: center;
        }
        
        .status-item {
            display: flex;
            justify-content: space-between;
            padding: 12px 0;
            border-bottom: 1px solid rgba(255, 255, 255, 0.2);
            font-size: 16px;
        }
        
        .status-item:last-child {
            border-bottom: none;
        }
        
        .status-label {
            font-weight: bold;
        }
        
        .status-value {
            color: #90EE90;
        }
        
        .success-icon {
            color: #90EE90;
            font-size: 20px;
            margin-right: 8px;
        }
        
        .next-steps {
            background: rgba(255, 215, 0, 0.2);
            border-radius: 15px;
            padding: 25px;
            margin-top: 20px;
            border: 2px solid rgba(255, 215, 0, 0.5);
        }
        
        .next-steps h3 {
            color: #ffd700;
            margin-bottom: 15px;
        }
        
        .next-steps p {
            font-size: 18px;
            line-height: 1.6;
        }
        
        .timestamp {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
            opacity: 0.8;
        }
        
        @media (max-width: 600px) {
            h1 {
                font-size: 32px;
            }
            .container {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🛒 E-commerce Platform</h1>
        <p class="subtitle">Online Shopping Portal with Multi-User System</p>
        
        <div class="status-box">
            <h3>✅ Phase 1 Setup Complete!</h3>
            
            <div class="status-item">
                <span class="status-label">
                    <span class="success-icon">✓</span> Environment Setup:
                </span>
                <span class="status-value">Done</span>
            </div>
            
            <div class="status-item">
                <span class="status-label">
                    <span class="success-icon">✓</span> Maven Project:
                </span>
                <span class="status-value">Created</span>
            </div>
            
            <div class="status-item">
                <span class="status-label">
                    <span class="success-icon">✓</span> Database (MySQL):
                </span>
                <span class="status-value">Connected</span>
            </div>
            
            <div class="status-item">
                <span class="status-label">
                    <span class="success-icon">✓</span> Tomcat Server:
                </span>
                <span class="status-value">Running</span>
            </div>
            
            <div class="status-item">
                <span class="status-label">
                    <span class="success-icon">✓</span> Dependencies:
                </span>
                <span class="status-value">Loaded</span>
            </div>
            
            <div class="status-item">
                <span class="status-label">
                    <span class="success-icon">✓</span> Project Structure:
                </span>
                <span class="status-value">Ready</span>
            </div>
        </div>
        
        <div class="next-steps">
            <h3>🚀 Ready for Phase 2</h3>
            <p><strong>Next Steps:</strong> Core Java Classes Development</p>
            <p style="margin-top: 10px;">
                • Model Classes (POJOs)<br>
                • Database Utility Classes<br>
                • DAO Layer Implementation
            </p>
        </div>
        
        <div class="timestamp">
            <strong>Server Time:</strong> <%= new java.util.Date() %>
        </div>
    </div>
</body>
</html>