USE master
GO
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'MEALS_AND_HEALTH')
BEGIN
	CREATE DATABASE MEALS_AND_HEALTH
END
GO
USE MEALS_AND_HEALTH

CREATE TABLE [Role] (
  [Id] int PRIMARY KEY IDENTITY,
  [Name] nvarchar(255)
);

CREATE TABLE [UserAccount] (
  [Id] int PRIMARY KEY IDENTITY,
  [Username] nvarchar(120),
  [Password] varchar(30),
  [PhoneNumber] char(10) NULL,
  [Email] varchar(120) UNIQUE,
  [IsActive] BIT NOT NULL DEFAULT 1,
  [RoleId] int,
  CONSTRAINT [FK_UserAccount.RoleId]
    FOREIGN KEY ([RoleId])
      REFERENCES [Role]([Id])
);

CREATE UNIQUE NONCLUSTERED INDEX UNIQUE_PHONE_NOT_NULL
ON UserAccount(PhoneNumber)
WHERE PhoneNumber IS NOT NULL

CREATE TABLE [CustomerAddress] (
  [Id] int PRIMARY KEY IDENTITY,
  [CustomerId] int,
  [Street] nvarchar(255),
  [Ward] nvarchar(255),
  [District] nvarchar(255),
  [City] nvarchar(255),
  CONSTRAINT [FK_CustomerAddress.CustomerId]
    FOREIGN KEY ([CustomerId])
      REFERENCES [UserAccount]([Id])
);

CREATE TABLE [Discount] (
  [Id] int PRIMARY KEY IDENTITY,
  [Description] nvarchar(255),
  [Percentage] DECIMAL(3,2),
  [StartDate] DATE,
  [EndDate] DATE,
  [IsValid] BIT NOT NULL DEFAULT 1
);

CREATE TABLE [Meal] (
  [Id] int PRIMARY KEY IDENTITY,
  [Name] nvarchar(255),
  [Description] nvarchar(400),
  [Image] varchar(255),
  [Price] DECIMAL,
  [IsAvailable] BIT NOT NULL DEFAULT 1,
  [RecipeDescription] nvarchar(MAX)
);

CREATE TABLE [MealDiscount] (
  [MealId] int PRIMARY KEY IDENTITY,
  [DiscountId] int,
  [ApplyDate] DATE,
  [CancelDate] DATE,
  CONSTRAINT [FK_MealDiscount.DiscountId]
    FOREIGN KEY ([DiscountId])
      REFERENCES [Discount]([Id]),
  CONSTRAINT [FK_MealDiscount.MealId]
    FOREIGN KEY ([MealId])
      REFERENCES [Meal]([Id])
);

CREATE TABLE [Personal Meal Plan] (
  [Id] int PRIMARY KEY IDENTITY,
  [Name] nvarchar(255),
  [Description] nvarchar(500),
  [Image] varchar(255),
  [CustomerId] int,
  CONSTRAINT [FK_Personal Meal Plan.CustomerId]
    FOREIGN KEY ([CustomerId])
      REFERENCES [UserAccount]([Id])
);

CREATE TABLE [MealType] (
  [Id] int PRIMARY KEY IDENTITY,
  [Name] varchar(80)
);

CREATE TABLE [Personal Meal Plan Detail] (
  [Id] int PRIMARY KEY IDENTITY,
  [PlanMealId] int,
  [MealId] int,
  [TypeId] int,
  [DaysOfTheWeek] int,
  CONSTRAINT [FK_Personal Meal Plan Detail.MealId]
    FOREIGN KEY ([MealId])
      REFERENCES [Meal]([Id]),
  CONSTRAINT [FK_Personal Meal Plan Detail.PlanMealId]
    FOREIGN KEY ([PlanMealId])
      REFERENCES [Personal Meal Plan]([Id]),
  CONSTRAINT [FK_Personal Meal Plan Detail.TypeId]
    FOREIGN KEY ([TypeId])
      REFERENCES [MealType]([Id])
);

CREATE TABLE [DaysOfTheWeek] (
  [Id] int PRIMARY KEY IDENTITY,
  [Name] varchar(9)
);

CREATE TABLE [ShipCompany] (
  [Id] int PRIMARY KEY IDENTITY,
  [Name] nvarchar(120),
  [PhoneNumber] char(10)
);

CREATE TABLE [OrderStatus] (
  [Id] int PRIMARY KEY IDENTITY,
  [Name] nvarchar(30)
);

CREATE TABLE [Order] (
  [Id] int PRIMARY KEY IDENTITY,
  [CustomerId] int,
  [OrderDate] DATE,
  [ShipVia] int,
  [Street] nvarchar(255),
  [Ward] nvarchar(255),
  [District] nvarchar(255),
  [City] nvarchar(255),
  [Total] DECIMAL,
  [StatusId] int,
  CONSTRAINT [FK_Order.CustomerId]
    FOREIGN KEY ([CustomerId])
      REFERENCES [UserAccount]([Id]),
  CONSTRAINT [FK_Order.ShipVia]
    FOREIGN KEY ([ShipVia])
      REFERENCES [ShipCompany]([Id]),
  CONSTRAINT [FK_Order.StatusId]
    FOREIGN KEY ([StatusId])
      REFERENCES [OrderStatus]([Id])
);

CREATE TABLE [OrderDetail] (
  [OrderId] int,
  [MealId] int,
  [UnitPrice] DECIMAL,
  [Quantity] int,
  [DiscountId] int,
  PRIMARY KEY([OrderId], [MealId]),
  CONSTRAINT [FK_OrderDetail.DiscountId]
    FOREIGN KEY ([DiscountId])
      REFERENCES [Discount]([Id]),
  CONSTRAINT [FK_OrderDetail.MealId]
    FOREIGN KEY ([MealId])
      REFERENCES [Meal]([Id]),
  CONSTRAINT [FK_OrderDetail.OrderId]
    FOREIGN KEY ([OrderId])
      REFERENCES [Order]([Id])
);

CREATE TABLE [Weekly Meal] (
  [Id] int PRIMARY KEY IDENTITY,
  [Name] nvarchar(255),
  [Description] nvarchar(500),
  [Image] varchar(255)
);

CREATE TABLE [Weekly Meal Detail] (
  [Id] int PRIMARY KEY IDENTITY,
  [PlanMealId] int,
  [MealId] int,
  [TypeId] int,
  [DaysOfTheWeek] int,
  CONSTRAINT [FK_Weekly Meal Detail.MealId]
    FOREIGN KEY ([MealId])
      REFERENCES [Meal]([Id]),
  CONSTRAINT [FK_Weekly Meal Detail.TypeId]
    FOREIGN KEY ([TypeId])
      REFERENCES [MealType]([Id]),
  CONSTRAINT [FK_Weekly Meal Detail.PlanMealId]
    FOREIGN KEY ([PlanMealId])
      REFERENCES [Weekly Meal]([Id])
);

CREATE TABLE [Ingredient] (
  [Id] int PRIMARY KEY IDENTITY,
  [Name] nvarchar(120)
);

CREATE TABLE [Unit] (
  [Id] int PRIMARY KEY IDENTITY,
  [Name] nvarchar(30)
);

CREATE TABLE [MealIngredient] (
  [MealId] int,
  [IngredientId] int,
  [Quantity] varchar(10),
  [UnitId] int,
  PRIMARY KEY ([MealId], [IngredientId]),
  CONSTRAINT [FK_MealIngredient.IngredientId]
    FOREIGN KEY ([IngredientId])
      REFERENCES [Ingredient]([Id]),
  CONSTRAINT [FK_MealIngredient.UnitId]
    FOREIGN KEY ([UnitId])
      REFERENCES [Unit]([Id]),
  CONSTRAINT [FK_MealIngredient.MealId]
    FOREIGN KEY ([MealId])
      REFERENCES [Meal]([Id])
);

CREATE TABLE [Keyword] (
  [Id] int PRIMARY KEY IDENTITY,
  [Name] nvarchar(255)
);

CREATE TABLE [MealKeywordMapping] (
  [KeywordId] int,
  [MealId] int,
  PRIMARY KEY ([KeywordId], [MealId]),
  CONSTRAINT [FK_MealKeywordMapping.KeywordId]
    FOREIGN KEY ([KeywordId])
      REFERENCES [Keyword]([Id]),
  CONSTRAINT [FK_MealKeywordMapping.MealId]
    FOREIGN KEY ([MealId])
      REFERENCES [Meal]([Id])
);

