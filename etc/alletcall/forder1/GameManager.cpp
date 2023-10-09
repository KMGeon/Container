#include "pch.h"
#include "GameManager.h"
#include <iostream>
#include "Enemy.h"
#include "Bullet.h"
using namespace std;

GameManager::GameManager() //���� ��ü�� ����
{
	gameObjects = vector<GameObject*>();
	player = new Player(COORD{ 15,20 });
	gameObjects.push_back(new Enemy(COORD{ 7,5 }, this));
	gameObjects.push_back(new Enemy(COORD{ 14,5 }, this));
	gameObjects.push_back(new Enemy(COORD{ 28,5 }, this));
	gameObjects.push_back(new Enemy(COORD{ 7,10 }, this));
	gameObjects.push_back(new Enemy(COORD{ 14,10 }, this));
	gameObjects.push_back(new Enemy(COORD{ 21,10 }, this));
	gameObjects.push_back(new Enemy(COORD{ 28,10 }, this));
	gameObjects.push_back(new Enemy(COORD{ 21,5 }, this));
	score = 0;
	isPlaying = 1;
	isWin = 0;
}

GameManager::~GameManager()
{
}

void GameManager::Board() //�� �ֺ� ����� ���⼭��
{
	cout << "|          Score:        "<<score<<"                 | " << endl;
	cout << "|------------------------------------------| " << endl;
	for (int i = 0; i < 20; i++)
		cout << "|                                          |" << endl;
	cout << "|------------------------------------------| " << endl;
}

void GameManager::tick() 
{
	CheckInput(); //�Է��� ����
	if (rand() % 2 == 0) //������ �¿�� �̵��� �� ���� ���� x���� ��������
	{
		movePosX = 1;
	}
	else
	{
		movePosX = -1;
	}

	player->tick(); //����� �����, ���߿� �ʿ������� �𸣴� �����׽��ϴ�.
	for (int i = 0; i < gameObjects.size(); i++) {
		gameObjects[i]->tick(); //�÷��̾ ������ ������Ʈ�� �����̰� �մϴ�.
	}
	checkCoilision(); //�浹üũ, �� �Լ��� �����ϸ� �˴ϴ�.
	drawCall(); //ȭ�鿡 ������Ʈ���� �׷���
}

void GameManager::drawCall()
{
	Board();
	player->draw();
	for (int i = 0; i < gameObjects.size(); i++) {
		gameObjects[i]->draw();
	}

}

void GameManager::CheckInput()
{
	if (GetAsyncKeyState(VK_LEFT) & (1 << 15))
	{
		player->move({ -1,0 });
	}
	if (GetAsyncKeyState(VK_RIGHT) & (1 << 15))
	{
		player->move({ 1,0 });
	}
	if (GetAsyncKeyState(VK_UP) & (1 << 15))
	{
		player->move({ 0,-1 });
	}
	if (GetAsyncKeyState(VK_DOWN) & (1 << 15))
	{
		player->move({ 0,1 });
	}
	if (GetAsyncKeyState(VK_SPACE) && 1)
	{
		gameObjects.push_back(player->shoot());
	}
}

void GameManager::checkCoilision()
{
	for (int i = 0; i < gameObjects.size(); i++) {
		gameObjects[i]->checkCollision();
	}
}

void GameManager::win() //�̰�����, NEW START������ ���ø� �˼��ֽ��ϴ�.
{
	isPlaying = 0;
	isWin = 1;
}

void GameManager::lose() //������, NEW START������ ���ø� �˼��ֽ��ϴ�.
{
	isPlaying = 0;
	isWin = 0;
}
void GameManager::deleteStage()
{
	delete(player);
	for (int i = 0; i < gameObjects.size(); i++) {
		delete(gameObjects[i]);
	}

}